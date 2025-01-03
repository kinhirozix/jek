package com.kinhiro.jek.compiler

import org.jetbrains.kotlin.config.CompilerConfigurationKey
import org.jetbrains.kotlin.name.FqName

object JekConstants {
    object CompilerPlugin {
        const val ID: String = "me.kinhiro.jek"

        @JvmField
        val VERSION: String = JekConstants::class.java.`package`.implementationVersion ?: "0.0.1"
    }

    object FqNames {
        @JvmField
        val JVM_PACKAGE_PRIVATE: FqName = FqName("com.kinhiro.jek.annotation.JvmPackagePrivate")
    }

    object OptionNames {
        const val ENABLED: String = "enabled"
        const val DEBUG: String = "debug"
    }

    object ConfigurationKeys {
        val ENABLED: CompilerConfigurationKey<Boolean> =
            CompilerConfigurationKey.create("Enable/disable JEK compiler plugin on the given compilation")

        val DEBUG: CompilerConfigurationKey<Boolean> =
            CompilerConfigurationKey.create("Enable/disable debug logging on the given compilation")
    }
}
