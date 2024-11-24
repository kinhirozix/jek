package me.kinhiro.jek

import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration

class JekCompilerPluginRegistrar : CompilerPluginRegistrar() {
    override val supportsK2: Boolean get() = true
    override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
        if (!configuration.getBoolean(JekConfigKeys.ENABLED)) return
        val debug = configuration.getBoolean(JekConfigKeys.DEBUG)
    }
}
