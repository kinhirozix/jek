plugins {
    kotlin("jvm")
    id("maven-publish")
}

base.archivesName = "jek-compiler-plugin"
description = "Just Enough Kinhiro (Kotlin Compiler Plugin)"

kotlin {
    sourceSets.all {
        languageSettings {
            optIn("org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi")
        }
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.compiler.embeddable)
}

tasks {
    jar {
        manifest {
            attributes(
                "Implementation-Version" to project.version
            )
        }
    }
}
