package me.kinhiro.jek.gradle.extension

import me.kinhiro.jek.gradle.Constants.Extensions
import me.kinhiro.jek.gradle.GradleProperties
import me.kinhiro.jek.gradle.JustEnoughKinhiro
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Property
import javax.inject.Inject

@JustEnoughKinhiro
abstract class MavenPublishExtension @Inject constructor(
    private val objects: ObjectFactory
) : ExtensionAware {
    val groupId: Property<String> get() = objects.property(String::class.java)
    val artifactId: Property<String> get() = objects.property(String::class.java)
    val version: Property<String> get() = objects.property(String::class.java)

    companion object : Registrable<MavenPublishExtension> {
        private val REGEX: Regex = Regex("""([a-z])([A-Z])""")

        override fun register(project: Project, target: Any): MavenPublishExtension = target.configureExtension(
            Extensions.MAVEN_PUBLISH
        ) {
            val groupProvider = project.providers.gradleProperty(GradleProperties.MAVEN_GROUP)
                .orElse(project.group.toString())
            groupId.convention(groupProvider)
            val moduleProvider = project.providers.gradleProperty(GradleProperties.MAVEN_MODULE)
                .orElse(project.baseExtension.archivesName.orElse(project.name.toArtifactId()))
        }

        private fun String.toArtifactId(): String = replace(REGEX, "$1-$2").replace('_', '-').lowercase()
    }
}
