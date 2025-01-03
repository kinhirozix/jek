package com.kinhiro.jek.compiler

import com.kinhiro.jek.compiler.JekConstants.ConfigurationKeys
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration

class JekCompilerPluginRegistrar : CompilerPluginRegistrar() {
    override val supportsK2: Boolean get() = true
    override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
        if (!configuration.getBoolean(ConfigurationKeys.ENABLED)) return
        val debug = configuration.getBoolean(ConfigurationKeys.DEBUG)
    }
}
