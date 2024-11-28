plugins {
    kotlin("jvm")
    `java-gradle-plugin`
    `maven-publish`
    id("com.gradle.plugin-publish")
}

base.archivesName = "jek-gradle-plugin"
description = "Just Enough Kinhiro (Gradle Plugin)"

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(project(":compiler-plugin"))
    implementation(libs.kotlin.gradle.plugin.api)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.xmlutil.serialization)
}

gradlePlugin {
    plugins {
        create("jekBasePlugin") {
            id = "me.kinhiro.jek.base"
            displayName = "Just Enough Kinhiro Base Gradle Plugin"
            description = project.description
            tags = listOf("")
            implementationClass = "me.kinhiro.jek.gradle.plugin.JekBasePlugin"
        }

        create("jekPlugin") {
            id = "me.kinhiro.jek"
            displayName = "Just Enough Kinhiro Gradle Plugin"
            description = project.description
            tags = listOf()
            implementationClass = "me.kinhiro.jek.gradle.plugin.JekPlugin"
        }
    }
}
