pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

if (JavaVersion.current().ordinal + 1 < 21) throw IllegalStateException("Please use Java 21+!")

rootProject.name = "build-logic"
