package com.kinhiro.jek.gradle.extension

import me.kinhiro.jek.gradle.JekConstants.Extensions
import me.kinhiro.jek.gradle.JustEnoughKinhiroDsl
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.plugins.ExtensionAware
import javax.inject.Inject

@JustEnoughKinhiroDsl
abstract class JekExtension @Inject constructor(
    private val objects: ObjectFactory,
    private val project: Project
) : ExtensionAware {
    companion object : Registrable<JekExtension> {
        override fun register(project: Project, target: Any): JekExtension = target.configureExtension<JekExtension>(
            Extensions.JEK
        )
    }
}
