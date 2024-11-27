package me.kinhiro.jek.gradle

import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory

internal fun <T : Any> ProviderFactory.gradleProperty(property: GradleProperties<T>): Provider<T> =
    gradleProperty(property.toString()).map { value ->
        @Suppress("UNCHECKED_CAST")
        when (property.defaultValue) {
            is Boolean -> value.toBoolean()
            is Int -> value.toInt()
            is String -> value
            else -> value
        } as T
    }.orElse(property.defaultValue)
