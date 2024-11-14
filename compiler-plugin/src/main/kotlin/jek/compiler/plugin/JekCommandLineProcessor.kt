/*
 * Copyright 2024 Kinhiro and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jek.compiler.plugin

import jek.util.JekConstants
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.config.CompilerConfiguration

class JekCommandLineProcessor : CommandLineProcessor {
    override val pluginId: String get() = JekConstants.PLUGIN_ID
    override val pluginOptions: Collection<AbstractCliOption> get() = listOf(OPTION_ENABLED, OPTION_DEBUG)
    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) =
        when (option.optionName) {
            "enabled" -> configuration.put(JekConfigurationKeys.ENABLED, value.toBoolean())
            "debug" -> configuration.put(JekConfigurationKeys.DEBUG, value.toBoolean())
            else -> error("Unexpected plugin option: ${option.optionName}.")
        }

    companion object {
        internal val OPTION_ENABLED: CliOption = CliOption(
            optionName = "enabled",
            valueDescription = "<true|false>",
            description = JekConfigurationKeys.ENABLED.toString(),
            required = true,
            allowMultipleOccurrences = false
        )

        internal val OPTION_DEBUG: CliOption = CliOption(
            optionName = "debug",
            valueDescription = "<true|false>",
            description = JekConfigurationKeys.DEBUG.toString(),
            required = true,
            allowMultipleOccurrences = false
        )
    }
}
