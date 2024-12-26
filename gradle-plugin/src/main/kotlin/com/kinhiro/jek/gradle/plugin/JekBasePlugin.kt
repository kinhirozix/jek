package com.kinhiro.jek.gradle.plugin

import me.kinhiro.jek.gradle.JekConstants
import me.kinhiro.jek.gradle.JekConstants.Extensions
import me.kinhiro.jek.gradle.JekConstants.Plugins
import me.kinhiro.jek.gradle.extension.*
import me.kinhiro.jek.gradle.gradleProperty
import me.kinhiro.jek.gradle.util.Logger
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.plugins.ide.idea.IdeaPlugin
import org.gradle.plugins.ide.idea.model.IdeaModel

abstract class JekBasePlugin : Plugin<Project> {
    private val logger: Logger = Logger(JekBasePlugin::class.java)

    override fun apply(target: Project) {
        with(target.plugins) {
            // https://docs.gradle.org/current/userguide/idea_plugin.html
            apply(IdeaPlugin::class.java)
            // https://docs.gradle.org/current/userguide/publishing_maven.html
            apply(MavenPublishPlugin::class.java)
        }

        with(target.pluginManager) {
            withPlugin(Plugins.External.IDEA) {
                target.extensions.configureExtension<IdeaModel>(Extensions.External.IDEA) {
                    module.isDownloadSources = target.providers.gradleProperty(GradleProperties.DownloadSources)
                        .getOrElse(true)
                    module.excludeDirs.add(target.rootDir.resolve(JekConstants.CACHE_DIR))
                }
            }
        }

        with(target.extensions) {
            JekExtension.register(target, target).let { jekExtension ->
                BridgeExtension.register(target, jekExtension)
                PublishExtension.register(target, jekExtension).let { publishExtension ->
                }
            }

            JekRepositoriesExtension.register(target, target.repositories)
        }

        with(target.configurations) {
        }

        with(target.repositories) {
        }

        with(target.dependencies) {
        }

        with(target.tasks) {
        }
    }
}
