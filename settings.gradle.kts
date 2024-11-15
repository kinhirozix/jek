@file:Suppress("SpellCheckingInspection")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

if (JavaVersion.current().ordinal + 1 < 21) throw IllegalStateException("Please use Java 21+!")

rootProject.name = "jek"

include(":compiler-plugin", ":compiler-plugin-embeddable", ":gradle-plugin", ":idea-plugin")
