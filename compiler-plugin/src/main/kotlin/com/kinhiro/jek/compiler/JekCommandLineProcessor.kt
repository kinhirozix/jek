package com.kinhiro.jek.compiler

import com.kinhiro.jek.compiler.JekConstants.CompilerPlugin
import com.kinhiro.jek.compiler.JekConstants.ConfigurationKeys
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CliOptionProcessingException
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.config.CompilerConfiguration

class JekCommandLineProcessor : CommandLineProcessor {
    override val pluginId: String get() = CompilerPlugin.ID
    override val pluginOptions: Collection<AbstractCliOption> get() = listOf(OPTION_ENABLED, OPTION_DEBUG)
    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) =
        when (option.optionName) {
            JekConstants.OptionNames.ENABLED -> configuration.put(ConfigurationKeys.ENABLED, value.toBoolean())
            JekConstants.OptionNames.DEBUG -> configuration.put(ConfigurationKeys.DEBUG, value.toBoolean())
            else -> throw CliOptionProcessingException("Unknown option: ${option.optionName}.")
        }

    private companion object {
        private val OPTION_ENABLED: CliOption = CliOption(
            JekConstants.OptionNames.ENABLED,
            "<true|false>",
            ConfigurationKeys.ENABLED.toString(),
            false,
            false
        )

        private val OPTION_DEBUG: CliOption = CliOption(
            JekConstants.OptionNames.DEBUG,
            "<true|false>",
            ConfigurationKeys.DEBUG.toString(),
            false,
            false
        )
    }
}
