package com.kinhiro.jek.gradle

import org.gradle.util.GradleVersion

object JekConstants {
    const val CACHE_DIR: String = ".jek"

    object Plugin {
        const val ID: String = "me.kinhiro.jek"
        const val NAME: String = "Just Enough Kinhiro Gradle Plugin"
    }

    object Plugins {
        const val BASE: String = "${Plugin.ID}.base"

        data object External {
            const val IDEA: String = "idea"
        }
    }

    object Extensions {
        const val JEK: String = "jek"
        const val BRIDGE: String = "bridge"
        const val PUBLISH: String = "publish"

        object External {
            const val IDEA: String = "idea"
        }
    }

    object Configurations {
        const val LOCALIZED: String = "localized"
        const val JAR_IN_JAR: String = "jarInJar"

        /**
         * See:
         * - [Java Plugin Configurations](https://docs.gradle.org/current/userguide/java_plugin.html#resolvable_configurations)
         * - [Java Library Plugin Configurations](https://docs.gradle.org/current/userguide/java_library_plugin.html#sec:java_library_configurations_graph)
         */
        object External {
            const val IMPLEMENTATION: String = "implementation"
            const val COMPILE_ONLY: String = "compileOnly"
            const val RUNTIME_ONLY: String = "runtimeOnly"
            const val TEST_IMPLEMENTATION: String = "testImplementation"
            const val TEST_COMPILE_ONLY: String = "testCompileOnly"
            const val TEST_RUNTIME_ONLY: String = "testRuntimeOnly"
            const val ANNOTATION_PROCESSOR: String = "annotationProcessor"
            const val COMPILE_CLASSPATH: String = "compileClasspath"
            const val RUNTIME_CLASSPATH: String = "runtimeClasspath"
            const val TEST_COMPILE_CLASSPATH: String = "testCompileClasspath"
            const val TEST_RUNTIME_CLASSPATH: String = "testRuntimeClasspath"
            const val API: String = "api"
            const val COMPILE_ONLY_API: String = "compileOnlyApi"
            const val API_ELEMENTS: String = "apiElements"
            const val RUNTIME_ELEMENTS: String = "runtimeElements"
        }
    }

    object Links {
        const val GITHUB_REPOSITORY: String = "https://github.com/kinhirozix/jek"
    }

    object Constraints {
        val MINIMAL_GRADLE_VERSION: GradleVersion = GradleVersion.version("8.12")
    }

    object GradleProperties {
        private const val PREFIX: String = "com.kinhiro.jek"
        const val USE_MAVEN_PERSONAL: String = "$PREFIX.use.maven.personal"
        const val DOWNLOAD_SOURCES: String = "$PREFIX.download.sources"
    }
}
