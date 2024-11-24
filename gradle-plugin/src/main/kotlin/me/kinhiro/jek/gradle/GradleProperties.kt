package me.kinhiro.jek.gradle

import me.kinhiro.jek.gradle.Constants.Plugin

sealed class GradleProperties<T : Any>(val defaultValue: T) {
    override fun toString(): String = this::class.java.simpleName.replaceFirstChar(Char::lowercase)
        .let { name -> "${Plugin.ID}.$name" }

    object DownloadSources : GradleProperties<Boolean>(true)
    object UseMavenPersonal : GradleProperties<Boolean>(false)
}
