package me.kinhiro.jek.gradle.plugin

import me.kinhiro.jek.gradle.GradleProperties
import me.kinhiro.jek.gradle.JekConstants
import me.kinhiro.jek.gradle.JekConstants.Plugins
import me.kinhiro.jek.gradle.extension.BridgeExtension
import me.kinhiro.jek.gradle.extension.JekExtension
import me.kinhiro.jek.gradle.extension.JekRepositoriesExtension
import me.kinhiro.jek.gradle.extension.PublishExtension
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
        logger.lifecycle("")
        target.checkSupportedVersion()
        val plugins = target.plugins
        val pluginManager = target.pluginManager
        val extensions = target.extensions
        val repositories = target.repositories
        val configurations = target.configurations
        val dependencies = target.dependencies
        val tasks = target.tasks

        with(plugins) {
            // https://docs.gradle.org/current/userguide/idea_plugin.html
            apply(IdeaPlugin::class.java)
            // https://docs.gradle.org/current/userguide/publishing_maven.html
            apply(MavenPublishPlugin::class.java)
        }

        with(pluginManager) {
            withPlugin(Plugins.External.IDEA) {
                extensions.configure(IdeaModel::class.java) { idea ->
                    idea.module
                    idea.module { module ->
                        module.isDownloadSources = target.providers.gradleProperty(GradleProperties.DownloadSources)
                            .getOrElse(true)
                        module.excludeDirs.add(target.rootDir.resolve(JekConstants.CACHE_DIR))
                    }
                }
            }
        }

        with(extensions) {
            JekExtension.register(target, target).let { jekExtension ->
                BridgeExtension.register(target, jekExtension)
                PublishExtension.register(target, jekExtension).let { publishExtension ->
                }
            }

            JekRepositoriesExtension.register(target, target.repositories)
        }

        with(configurations) {
        }

        with(repositories) {
        }

        with(dependencies) {
        }

        with(tasks) {
        }
    }
}
