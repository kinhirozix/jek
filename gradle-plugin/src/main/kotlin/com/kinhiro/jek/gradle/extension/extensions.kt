package com.kinhiro.jek.gradle.extension

import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware

internal interface Registrable<T> {
    fun register(project: Project, target: Any): T
}

internal inline fun <reified T : Any> Any.configureExtension(
    name: String,
    vararg constructionArguments: Any,
    noinline configuration: T.() -> Unit = {}
): T = with((this as ExtensionAware).extensions) {
    (findByName(name) as? T ?: create(name, T::class.java, *constructionArguments)).apply(configuration)
}
