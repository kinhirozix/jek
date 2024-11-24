package me.kinhiro.jek

import org.jetbrains.kotlin.name.FqName

object JekConstants {
    const val PLUGIN_ID: String = "me.kinhiro.jek"
    val PLUGIN_VERSION: String = JekConstants::class.java.`package`.implementationVersion ?: "0.0.1"

    internal object FqNames {
        val JVM_PACKAGE_PRIVATE: FqName = FqName("me.kinhiro.jek.annotation.JvmPackagePrivate")
    }

    object OptionNames {
        const val ENABLED: String = "enabled"
        const val DEBUG: String = "debug"
    }
}
