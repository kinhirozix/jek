@file:Suppress("SpellCheckingInspection")

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.intellij.platform) apply false
    alias(libs.plugins.changelog) apply false
    alias(libs.plugins.spotless)
}

idea {
    module {
        isDownloadSources = true
    }
}

group = property("maven_group").toString()
version = property("maven_version").toString()

spotless {
    kotlin {
        target("**/*.kt")
        licenseHeaderFile("$rootDir/spotless/license-header.txt", "^(package|@)")
        trimTrailingWhitespace()
        endWithNewline()
    }
}

repositories {
    mavenCentral()
}

tasks.named("check").get().dependsOn("spotlessCheck")
tasks.named("assemble").get().dependsOn("spotlessApply")

subprojects {
    apply(plugin = "idea")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = rootProject.group
    version = rootProject.version

    extensions.configure<JavaPluginExtension> {
        toolchain.languageVersion = JavaLanguageVersion.of(21)
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
        withSourcesJar()
    }

    extensions.configure<KotlinJvmProjectExtension> {
        jvmToolchain(21)

        sourceSets.all {
            languageSettings {
                optIn("kotlin.RequiresOptIn")
                optIn("org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi")
            }
        }
    }

    repositories {
        mavenCentral()
    }

    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
            options.release = 21
            sourceCompatibility = "21"
            targetCompatibility = "21"
        }

        withType<KotlinCompile> {
            compilerOptions {
                jvmTarget = JvmTarget.JVM_21
            }
        }

        named<Jar>("jar") {
            from(rootProject.file("LICENSE")) {
                rename("LICENSE", "LICENSE.txt")
            }
        }

        named<Jar>("sourcesJar") {
            from(rootProject.file("LICENSE")) {
                rename("LICENSE", "LICENSE.txt")
            }
        }
    }
}
