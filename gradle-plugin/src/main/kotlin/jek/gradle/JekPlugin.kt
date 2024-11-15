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
package jek.gradle

import jek.gradle.project.ProjectExtension
import jek.gradle.publish.PublishExtension
import jek.util.JekConstants
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.plugins.ide.idea.model.IdeaModel
import org.jetbrains.kotlin.gradle.plugin.*

class JekPlugin : KotlinCompilerPluginSupportPlugin {
    override fun apply(target: Project) {
        target.configureIdeaPlugin()
        val jekExtension = target.extensions.create("jek", JekExtension::class.java, target)
        jekExtension.extensions.create("project", ProjectExtension::class.java, target)
        jekExtension.extensions.create("publish", PublishExtension::class.java, target)
    }

    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>): Boolean {
        val target = kotlinCompilation.target
        val project = target.project
        return project.pluginManager.hasPlugin("org.jetbrains.kotlin.jvm") &&
            when (kotlinCompilation.platformType) {
                KotlinPlatformType.jvm -> true
                else -> false
            }
    }

    override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> {
        val target = kotlinCompilation.target
        return target.project.provider { emptyList() }
    }

    override fun getCompilerPluginId(): String = JekConstants.PLUGIN_ID
    override fun getPluginArtifact(): SubpluginArtifact = SubpluginArtifact(
        groupId = "me.kinhiro",
        artifactId = JekConstants.PLUGIN_ID,
        version = JekConstants.PLUGIN_VERSION
    )

    private fun Project.configureIdeaPlugin() {
        plugins.apply("idea")
        pluginManager.withPlugin("idea") {
            val ideaExtension = extensions.getByType(IdeaModel::class.java)
            if (!ideaExtension.module.isDownloadSources) extensions.configure(IdeaModel::class.java) { idea ->
                idea.module { module ->
                    module.isDownloadSources = true
                }
            }
        }
    }
}
