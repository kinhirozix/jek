package com.kinhiro.jek.gradle.extension

import me.kinhiro.jek.gradle.JekConstants.Extensions
import me.kinhiro.jek.gradle.JustEnoughKinhiroDsl
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.plugins.ExtensionAware
import javax.inject.Inject

@JustEnoughKinhiroDsl
abstract class PublishExtension @Inject constructor(
    private val objects: ObjectFactory
) : ExtensionAware {
    companion object : Registrable<PublishExtension> {
        override fun register(project: Project, target: Any): PublishExtension = target.configureExtension(
            Extensions.PUBLISH
        )
    }
}
