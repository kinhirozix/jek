package me.kinhiro.composite

import org.gradle.api.initialization.Settings
import org.gradle.api.initialization.dsl.VersionCatalogBuilder
import org.gradle.api.internal.file.FileOperations
import java.io.File
import java.io.FileNotFoundException
import javax.inject.Inject

class RootSettings @Inject constructor(private val fileOperations: FileOperations) : AbstractSettings() {
    override fun Settings.applyPlugin() {
        @Suppress("UnstableApiUsage")
        dependencyResolutionManagement { management ->
            management.repositories { repository ->
                repository.mavenCentral()
            }

            management.versionCatalogs { catalog ->
                val libs = catalog.maybeCreate("libs")
                val maybeFile = rootDir.resolveParent("gradle/libs.versions.toml")
                if (maybeFile != rootDir.resolve("gradle/libs.versions.toml"))
                    libs.from(fileOperations.immutableFiles(maybeFile))
                providers.gradlePropertiesPrefixedBy("override.libs.versions.").orNull?.forEach { key, version ->
                    val alias = key.substringAfter("override.libs.versions.")
                    if (!version.isNullOrEmpty()) libs.overrideVersion(alias, version)
                }
            }
        }
    }

    private fun File.resolveParent(relative: String): File = resolve(relative).takeIf { file -> file.exists() }
        ?: parentFile?.resolveParent(relative) ?: throw FileNotFoundException("File $relative not found!")

    private fun VersionCatalogBuilder.overrideVersion(alias: String, version: String) {
        version(alias, version)
        println(buildString {
            append("Overridden Version Catalogs")
            append("\u001b[95m libs.versions.toml\u001b[0m \u001b[93m[versions]\u001b[0m ")
            append("[ alias: \u001b[94m$alias\u001b[0m version: \u001b[92m$version\u001b[0m ]")
        })
    }
}
