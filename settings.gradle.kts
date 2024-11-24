@file:Suppress("SpellCheckingInspection")

import org.gradle.tooling.UnsupportedVersionException

pluginManagement {
    repositories {
        gradlePluginPortal()
    }

    includeBuild("./build-logic")
}

plugins {
    // Use the Foojay Toolchains plugin to automatically download JDKs required by subprojects.
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
    id("me.kinhiro.composite.settings")
}

if (JavaVersion.current() < JavaVersion.VERSION_21) throw UnsupportedVersionException("Please use Java 21+!")

rootProject.name = "jek"

include(":annotations", ":compiler-plugin", ":gradle-plugin")
