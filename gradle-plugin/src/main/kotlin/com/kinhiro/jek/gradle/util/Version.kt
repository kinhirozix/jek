package com.kinhiro.jek.gradle.util

data class Version(
    val major: Int = 0,
    val minor: Int = 0,
    val patch: Int = 0,
    val prerelease: String = "",
    val buildmetadata: String = "",
) : Comparable<Version> {
    override fun compareTo(other: Version): Int = (major - other.major).or { minor - other.minor }
        .or { patch - other.patch }
        .or { prereleaseCompareTo(prerelease, other.prerelease) }
        .or { prerelease.compareTo(other.prerelease, true) }

    override fun hashCode(): Int {
        var result = major
        result = 31 * result + minor
        result = 31 * result + patch
        result = 31 * result + prerelease.hashCode()
        result = 31 * result + buildmetadata.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Version
        if (major != other.major) return false
        if (minor != other.minor) return false
        if (patch != other.patch) return false
        if (prerelease != other.prerelease) return false
        if (buildmetadata != other.buildmetadata) return false
        if (!toString().equals(other.toString(), true)) return false
        return true
    }

    override fun toString(): String = buildString {
        append(major).append('.').append(minor).append('.').append(patch)
        if (prerelease.isNotEmpty()) append('-').append(prerelease)
        if (buildmetadata.isNotEmpty()) append('+').append(buildmetadata)
    }

    private inline fun Int.or(other: () -> Int): Int = takeIf { num -> num != 0 } ?: other()
    private fun prereleaseCompareTo(self: String, other: String): Int {
        if (self.isEmpty() && other.isNotEmpty()) return 1
        if (self.isNotEmpty() && other.isEmpty()) return -1
        if (self.isEmpty() && other.isEmpty()) return 0
        val selfParts = self.split('.')
        val otherParts = other.split('.')
        val maxLength = maxOf(selfParts.size, otherParts.size)
        for (i in 0..<maxLength) {
            val selfPart = selfParts.getOrNull(i) ?: return -1
            val otherPart = otherParts.getOrNull(i) ?: return 1 // Shorter prerelease is smaller
            val isSelfNumeric = selfPart.all { ch -> ch.isDigit() }
            val isOtherNumeric = selfPart.all { ch -> ch.isDigit() }
            val diff = when {
                isSelfNumeric && isOtherNumeric -> selfPart.toInt().compareTo(otherPart.toInt()) // Compare numerically
                isSelfNumeric -> -1 // Numeric is smaller than non-numeric
                isOtherNumeric -> 1
                else -> selfPart.compareTo(otherPart, true) // Lexicographical comparison
            }

            if (diff != 0) return diff
        }

        return 0 // All parts equal
    }

    companion object {
        private val VERSION_REGEX: Regex = Regex(
            "^(?<major>0|[1-9]\\d*)\\.(?<minor>0|[1-9]\\d*)\\.(?<patch>0|[1-9]\\d*)" +
                    "(?:-(?<prerelease>(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)" +
                    "(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?" +
                    "(?:\\+(?<buildmetadata>[0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?"
        )

        @JvmStatic
        fun parse(version: String): Version {
            val matcher = VERSION_REGEX.matchEntire(version)
                ?: throw IllegalArgumentException("Invalid version format: $version.")
            val major = matcher.groups["major"]?.value?.toInt() ?: 0
            val minor = matcher.groups["minor"]?.value?.toInt() ?: 0
            val patch = matcher.groups["patch"]?.value?.toInt() ?: 0
            val prerelease = matcher.groups["prerelease"]?.value ?: ""
            val buildmetadata = matcher.groups["buildmetadata"]?.value ?: ""
            return Version(major, minor, patch, prerelease, buildmetadata)
        }
    }
}
