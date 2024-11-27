plugins {
    kotlin("jvm")
    `java-gradle-plugin`
    `maven-publish`
}

base.archivesName = "jek-gradle-plugin"
description = "Just Enough Kinhiro (Gradle Plugin)"

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(project(":compiler-plugin"))
    implementation(libs.kotlin.gradle.plugin.api)
}

gradlePlugin {
    plugins {
        create("base") {
            id = "me.kinhiro.jek.base"
            implementationClass = "me.kinhiro.jek.gradle.plugin.JekBasePlugin"
            displayName = "Just Enough Kinhiro Base Gradle Plugin"
            description = project.description
        }

        create("jek") {
            id = "me.kinhiro.jek"
            implementationClass = "me.kinhiro.jek.gradle.plugin.JekPlugin"
            displayName = "Just Enough Kinhiro Gradle Plugin"
            description = project.description
        }
    }
}
