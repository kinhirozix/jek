@file:Suppress("SpellCheckingInspection")

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.shadow) apply false
    alias(libs.plugins.plugin.publish) apply false
    id("me.kinhiro.composite.root")
}

allprojects {
    group = providers.gradleProperty("maven.group").getOrElse("me.kinhiro.jek")
    version = providers.gradleProperty("maven.version").getOrElse("0.0.1")

    /**
     * Retrieves the Java version by checking the local build environment.
     * The value is determined as follows (in order of precedence):
     * 1. The value of the Gradle property `java.version`, if set.
     * 2. The default hardcoded value of `21`.
     */
    val javaVersion = providers.gradleProperty("java.version").getOrElse("21")
    val java = javaVersion.toInt()

    pluginManager.withPlugin("java") {
        extensions.configure<JavaPluginExtension> {
            toolchain.languageVersion = JavaLanguageVersion.of(java)
            sourceCompatibility = JavaVersion.toVersion(javaVersion)
            targetCompatibility = JavaVersion.toVersion(javaVersion)
            withSourcesJar()
        }

        tasks.withType<JavaCompile> {
            options.encoding = "UTF-8"
            options.release = java
            sourceCompatibility = javaVersion
            targetCompatibility = javaVersion
        }

        tasks.withType<Jar> {
            if (name in listOf("jar", "sourcesJar")) from(rootProject.file("LICENSE")) {
                rename("LICENSE", "LICENSE.txt")
            }
        }
    }

    /**
     * Retrieves the Kotlin version by checking the local build environment.
     * The value is determined as follows (in order of precedence):
     * 1. The value of the Gradle property `kotlin.version`, if set.
     * 2. The value of the `kotlin` key in the `versions` table of the root project's
     * `./gradle/libs.versions.toml`, if this file exists, the table is defined and key is set.
     * 3. The default hardcoded value of `2.0.21`.
     */
    val kotlinVersion = providers.gradleProperty("kotlin.version").orNull
        ?: rootProject.libs.versions.kotlin.orNull ?: "2.0.21"
    val kotlin = Regex("^(\\d+\\.\\d+)").find(kotlinVersion)?.groupValues?.get(0) ?: "2.0"
    val hasKotlinLanguageSettingsProperty = providers.gradleProperty("kotlin.language.settings")
        .getOrElse("true").toBoolean() == true

    pluginManager.withPlugin(rootProject.libs.plugins.kotlin.jvm.id) {
        extensions.configure<KotlinJvmProjectExtension> {
            sourceSets.all {
                languageSettings {
                    if (hasKotlinLanguageSettingsProperty) {
                        languageVersion = kotlin
                        apiVersion = kotlin
                    }

                    optIn("kotlin.RequiresOptIn")
                }
            }
        }

        tasks.withType<KotlinCompile> {
            compilerOptions {
                jvmTarget = JvmTarget.fromTarget(javaVersion)
                freeCompilerArgs = freeCompilerArgs.get() + listOf("-Xjvm-default=all")
                if (hasKotlinLanguageSettingsProperty) {
                    apiVersion = KotlinVersion.fromVersion(kotlin)
                    languageVersion = KotlinVersion.fromVersion(kotlin)
                }
            }
        }
    }
}
