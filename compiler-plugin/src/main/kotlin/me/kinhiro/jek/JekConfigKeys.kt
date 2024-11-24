package me.kinhiro.jek

import org.jetbrains.kotlin.config.CompilerConfigurationKey

internal object JekConfigKeys {
    val ENABLED: CompilerConfigurationKey<Boolean> =
        CompilerConfigurationKey.create("Enable/disable Just Enough Kinhiro compiler plugin on the given compilation")

    val DEBUG: CompilerConfigurationKey<Boolean> =
        CompilerConfigurationKey.create("Enable/disable debug logging on the given compilation")
}
