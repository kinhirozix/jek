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
}

gradlePlugin {
    plugins {
        create("jekBasePlugin") {
            id = "com.kinhiro.jek.base"
            displayName = "Just Enough Kinhiro Base Gradle Plugin"
            description = project.description
            tags = listOf("")
            implementationClass = "com.kinhiro.jek.gradle.plugin.JekBasePlugin"
        }

        create("jekPlugin") {
            id = "com.kinhiro.jek"
            displayName = "Just Enough Kinhiro Gradle Plugin"
            description = project.description
            tags = listOf()
            implementationClass = "com.kinhiro.jek.gradle.plugin.JekPlugin"
        }
    }
}

tasks {
    sourcesJar {
        from(sourceSets.main.get().allSource)
        archiveClassifier = "sources"
    }
}

artifacts {
    archives(tasks.sourcesJar)
}
