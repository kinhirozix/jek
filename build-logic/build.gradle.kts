@file:Suppress("SpellCheckingInspection")

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    alias(libs.plugins.kotlin.jvm)
    `java-gradle-plugin`
}

/**
 * Retrieves the Java version by checking the local build environment.
 * The value is determined as follows (in order of precedence):
 * 1. The value of the Gradle property `java.version`, if set.
 * 2. The default hardcoded value of `21`.
 */
val javaVersion = providers.gradleProperty("java.version").getOrElse("21")
val javaMajorVersion = javaVersion.toInt()

java {
    toolchain.languageVersion = JavaLanguageVersion.of(javaMajorVersion)
    sourceCompatibility = JavaVersion.toVersion(javaVersion)
    targetCompatibility = JavaVersion.toVersion(javaVersion)
    withSourcesJar()
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
val kotlinMajorVersion = Regex("^(\\d+\\.\\d+)").find(kotlinVersion)?.groupValues?.get(0) ?: "2.0"
val hasKotlinLanguageSettingsProperty = providers.gradleProperty("kotlin.language.settings")
    .getOrElse("true").toBoolean() == true

kotlin {
    sourceSets.all {
        languageSettings {
            if (hasKotlinLanguageSettingsProperty) {
                languageVersion = kotlinMajorVersion
                apiVersion = kotlinMajorVersion
            }

            optIn("kotlin.RequiresOptIn")
        }
    }
}

repositories {
    gradlePluginPortal()
}

dependencies {
    composite(libs.kotlin.stdlib)
    composite(libs.kotlin.reflect)
    composite(libs.annotations)
    composite(gradleApi())
    composite(gradleKotlinDsl())
    composite(files(libs::class.java.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        create("settings") {
            id = "com.kinhiro.composite.settings"
            implementationClass = "com.kinhiro.composite.RootSettings"
        }

        create("root") {
            id = "com.kinhiro.composite.root"
            implementationClass = "com.kinhiro.composite.RootPlugin"
        }
    }
}

fun DependencyHandler.composite(dependencyNotation: Any) {
    compileOnly(dependencyNotation)
    runtimeOnly(dependencyNotation)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release = javaMajorVersion
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(javaVersion)
        freeCompilerArgs = freeCompilerArgs.get() + listOf("-Xjvm-default=all")
        if (hasKotlinLanguageSettingsProperty) {
            apiVersion = KotlinVersion.fromVersion(kotlinMajorVersion)
            languageVersion = KotlinVersion.fromVersion(kotlinMajorVersion)
        }
    }
}
