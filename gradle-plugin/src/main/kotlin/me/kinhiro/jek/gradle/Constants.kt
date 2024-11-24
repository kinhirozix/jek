package me.kinhiro.jek.gradle

object Constants {
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
    }

    object Repositories {
        const val GITHUB_PERSONAL: String = "https://kinhirozix.github.io/maven"
        const val KINHIRO_RELEASES: String = "https://maven.kinhiro.me/releases"
        const val KINHIRO_SNAPSHOTS: String = "https://maven.kinhiro.me/snapshots"

        object Names {
            const val GITHUB_PERSONAL: String = "https://kinhirozix.github.io/maven"
            const val KINHIRO_RELEASES: String = "Kinhiro Zix Repository (Releases)"
            const val KINHIRO_SNAPSHOTS: String = "Kinhiro Zix Repository (Snapshots)"
        }
    }

    object Configurations {
        const val JAR_IN_JAR: String = "jarInJar"

        object Descriptions {
            const val JAR_IN_JAR: String = "Bundles dependencies into a single JAR for simplified use and isolation."
        }

        /**
         * See:
         * - [Java Plugin Configurations](https://docs.gradle.org/current/userguide/java_plugin.html#resolvable_configurations)
         * - [Java Library Plugin Configurations](https://docs.gradle.org/current/userguide/java_library_plugin.html#sec:java_library_configurations_graph)
         */
        object External {
            const val IMPLEMENTATION: String = "implementation"
            const val API: String = "api"
        }
    }

    object Dependencies {
        const val JEK_GROUP: String = "me.kinhiro.jek"
    }

    object Tasks {
        const val JAR_IN_JAR: String = Configurations.JAR_IN_JAR

        object External {
            const val CLEAN: String = "clean"
        }
    }

    object Links {
        const val GITHUB_REPO: String = "https://github.com/kinhirozix/jek"
    }
}
