package com.kinhiro.jek.gradle.extension

import org.gradle.api.Project
import org.gradle.api.plugins.BasePluginExtension
import org.gradle.api.plugins.ExtensionAware

internal val Project.baseExtension: BasePluginExtension get() = extensions.getByType(BasePluginExtension::class.java)

internal interface Registrable<T> {
    fun register(project: Project, target: Any): T
}

internal inline fun <reified T : Any> Any.configureExtension(
    name: String,
    vararg constructionArguments: Any,
    noinline configuration: T.() -> Unit = {}
): T = with(
    (this as? ExtensionAware)?.extensions
        ?: error("Object '$this' is not ExtensionAware. Unable to configure extension '$name'.")
) {
    (findByName(name) as? T ?: create(name, T::class.java, *constructionArguments)).apply(configuration)
}
