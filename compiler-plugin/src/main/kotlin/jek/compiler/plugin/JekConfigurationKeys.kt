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

import org.jetbrains.kotlin.config.CompilerConfigurationKey

object JekConfigurationKeys {
    val ENABLED: CompilerConfigurationKey<Boolean> = CompilerConfigurationKey
        .create("Enable/disable Just Enough Kinhiro compiler plugin on the given compilation")

    val DEBUG: CompilerConfigurationKey<Boolean> = CompilerConfigurationKey
        .create("Enable/disable debug logging on the given compilation")
}
