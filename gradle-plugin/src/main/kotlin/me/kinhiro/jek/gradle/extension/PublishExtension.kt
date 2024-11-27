package me.kinhiro.jek.gradle.extension

import me.kinhiro.jek.gradle.Constants.Extensions
import me.kinhiro.jek.gradle.JustEnoughKinhiro
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.plugins.ExtensionAware
import javax.inject.Inject

@JustEnoughKinhiro
abstract class PublishExtension @Inject constructor(
    private val objects: ObjectFactory
) : ExtensionAware {

    companion object : Registrable<PublishExtension> {
        override fun register(project: Project, target: Any): PublishExtension = target.configureExtension(
            Extensions.PUBLISH
        )
    }
}
