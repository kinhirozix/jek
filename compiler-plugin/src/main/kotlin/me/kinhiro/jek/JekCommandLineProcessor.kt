package me.kinhiro.jek

import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CliOptionProcessingException
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.config.CompilerConfiguration

class JekCommandLineProcessor : CommandLineProcessor {
    override val pluginId: String get() = JekConstants.PLUGIN_ID
    override val pluginOptions: Collection<AbstractCliOption> get() = listOf(OPTION_ENABLED, OPTION_DEBUG)
    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) =
        when (option.optionName) {
            JekConstants.OptionNames.ENABLED -> configuration.put(JekConfigKeys.ENABLED, value.toBoolean())
            JekConstants.OptionNames.DEBUG -> configuration.put(JekConfigKeys.DEBUG, value.toBoolean())
            else -> throw CliOptionProcessingException("Unknown option: ${option.optionName}.")
        }

    private companion object {
        private val OPTION_ENABLED: CliOption = CliOption(
            JekConstants.OptionNames.ENABLED,
            "<true|false>",
            JekConfigKeys.ENABLED.toString(),
            false,
            false
        )

        private val OPTION_DEBUG: CliOption = CliOption(
            JekConstants.OptionNames.DEBUG,
            "<true|false>",
            JekConfigKeys.DEBUG.toString(),
            false,
            false
        )
    }
}
