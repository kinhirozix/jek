@file:JvmSynthetic

package me.kinhiro.jek.gradle.plugin

import org.gradle.api.Project

internal fun Project.findProperties(name: String): String? = findProperty(name) as? String
internal fun Project.properties(name: String): String = findProperty(name) as? String
    ?: error("Property `$name` is not set!")
