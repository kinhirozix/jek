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
package com.kinhiro.jek.gradle.plugin

import me.kinhiro.jek.JekConstants
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

class JekPlugin : KotlinCompilerPluginSupportPlugin {
    override fun apply(target: Project) {
        with(target.plugins) {
            apply(JekBasePlugin::class.java)
        }
    }

    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>): Boolean = true
    override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> {
        val target = kotlinCompilation.target
        val project = target.project
        val parameters = mutableListOf<SubpluginOption>()
        return project.provider { parameters }
    }

    override fun getCompilerPluginId(): String = JekConstants.PLUGIN_ID
    override fun getPluginArtifact(): SubpluginArtifact = SubpluginArtifact(
        "me.kinhiro",
        "jek-compiler-plugin",
        JekConstants.PLUGIN_VERSION
    )
}
