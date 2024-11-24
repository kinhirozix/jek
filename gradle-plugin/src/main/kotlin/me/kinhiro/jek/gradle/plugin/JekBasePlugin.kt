package me.kinhiro.jek.gradle.plugin

import me.kinhiro.jek.gradle.Constants
import me.kinhiro.jek.gradle.Constants.Configurations
import me.kinhiro.jek.gradle.Constants.Plugins
import me.kinhiro.jek.gradle.GradleProperties
import me.kinhiro.jek.gradle.extension.BridgeExtension
import me.kinhiro.jek.gradle.extension.JekExtension
import me.kinhiro.jek.gradle.extension.JekRepositoriesExtension
import me.kinhiro.jek.gradle.gradleProperty
import me.kinhiro.jek.gradle.util.Logger
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.plugins.ide.idea.IdeaPlugin
import org.gradle.plugins.ide.idea.model.IdeaModel

abstract class JekBasePlugin : Plugin<Project> {
    private val logger: Logger = Logger(JekBasePlugin::class.java)

    override fun apply(target: Project) {
        logger.lifecycle("")

        with(target.plugins) {
            // https://docs.gradle.org/current/userguide/idea_plugin.html
            apply(IdeaPlugin::class.java)
        }

        with(target.pluginManager) {
            withPlugin(Plugins.External.IDEA) {
                target.extensions.configure(IdeaModel::class.java) { idea ->
                    idea.module { module ->
                        module.isDownloadSources = target.providers.gradleProperty(GradleProperties.DownloadSources)
                            .getOrElse(true)
                        module.excludeDirs.add(target.rootDir.resolve(Constants.CACHE_DIR))
                    }
                }
            }
        }

        with(target.extensions) {
            JekExtension.register(target, target).let { jekExtension ->
                BridgeExtension.register(target, jekExtension)
            }

            JekRepositoriesExtension.register(target, target.repositories)
        }

        with(target.configurations) {
            create(Configurations.JAR_IN_JAR).apply {
                description = Configurations.Descriptions.JAR_IN_JAR
            }
        }

        with(target.repositories) {
        }

        with(target.dependencies) {
        }

        with(target.tasks) {
        }
    }
}
