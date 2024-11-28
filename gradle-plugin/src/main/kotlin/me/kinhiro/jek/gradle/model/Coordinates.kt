package me.kinhiro.jek.gradle.model

data class Coordinates(val groupId: String, val artifactId: String) {
    override fun toString(): String = "$groupId:$artifactId"
}
