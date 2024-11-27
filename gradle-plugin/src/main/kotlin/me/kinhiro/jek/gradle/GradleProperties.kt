package me.kinhiro.jek.gradle

import me.kinhiro.jek.gradle.Constants.Plugin

sealed class GradleProperties<T : Any>(val defaultValue: T) {
    override fun toString(): String = this::class.java.simpleName.replaceFirstChar(Char::lowercase)
        .let { name -> "${Plugin.ID}.$name" }

    companion object {
        const val MAVEN_GROUP: String = "maven.group"
        const val MAVEN_MODULE: String = "maven.module"
        const val MAVEN_VERSION: String = "maven.version"
    }

    object DownloadSources : GradleProperties<Boolean>(true)
    object UseMavenPersonal : GradleProperties<Boolean>(false)
}
