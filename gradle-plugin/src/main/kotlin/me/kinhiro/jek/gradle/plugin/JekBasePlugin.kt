package me.kinhiro.jek.gradle.plugin

import me.kinhiro.jek.gradle.Constants
import me.kinhiro.jek.gradle.Constants.Configurations
import me.kinhiro.jek.gradle.Constants.Descriptions
import me.kinhiro.jek.gradle.Constants.Plugins
import me.kinhiro.jek.gradle.GradleProperties
import me.kinhiro.jek.gradle.extension.*
import me.kinhiro.jek.gradle.gradleProperty
import me.kinhiro.jek.gradle.util.Logger
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.plugins.ide.idea.IdeaPlugin
import org.gradle.plugins.ide.idea.model.IdeaModel

abstract class JekBasePlugin : Plugin<Project> {
    private val logger: Logger = Logger(JekBasePlugin::class.java)

    override fun apply(target: Project) {
        logger.lifecycle("")
        target.checkSupportedVersion()

        with(target.plugins) {
            // https://docs.gradle.org/current/userguide/idea_plugin.html
            apply(IdeaPlugin::class.java)
            // https://docs.gradle.org/current/userguide/publishing_maven.html
            apply(MavenPublishPlugin::class.java)
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
                PublishExtension.register(target, jekExtension).let { publishExtension ->
                    MavenPublishExtension.register(target, publishExtension).let { mavenPublishExtension ->
                        target.extensions.getByType(PublishingExtension::class.java).apply {
                            with(publications) {
                                create("maven", MavenPublication::class.java).apply {
                                    groupId = mavenPublishExtension.groupId.get()
                                    artifactId = mavenPublishExtension.artifactId.get()
                                    version = mavenPublishExtension.version.get()
                                    from(target.components.getByName("java"))
                                }
                            }
                        }
                    }
                }
            }

            JekRepositoriesExtension.register(target, target.repositories)
        }

        with(target.configurations) {
            val localized = maybeCreate(Configurations.LOCALIZED).apply {
            }

            val jarInJar = maybeCreate(Configurations.JAR_IN_JAR).apply {
                description = Descriptions.JAR_IN_JAR
            }
        }

        with(target.repositories) {
            mavenCentral()
        }

        with(target.dependencies) {
        }

        with(target.tasks) {
        }
    }
}
