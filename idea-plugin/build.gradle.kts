import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.intellij.platform")
    id("org.jetbrains.changelog")
}

description = "Just Enough Kinhiro (JB)"

idea {
    module {
        isDownloadSources = true
    }
}

repositories {
    intellijPlatform { defaultRepositories() }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.reflect)

    intellijPlatform {
        intellijIdeaCommunity(libs.versions.idea)
        instrumentationTools()
        pluginVerifier()
        bundledPlugins("com.intellij.java", "org.jetbrains.kotlin", "com.intellij.gradle")
    }
}

intellijPlatform {
    buildSearchableOptions = false
    autoReload = false

    pluginConfiguration {
        name = "Just Enough Kinhiro"
    }

    pluginVerification {
        ides {
            recommended()
        }
    }
}

tasks {
    compileJava {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    compileKotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_21
        }
    }

    runIde {
        systemProperty("idea.is.internal", true)
        systemProperty("idea.kotlin.plugin.use.k2", true)
        jvmArgumentProviders += CommandLineArgumentProvider {
            listOf("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005")
        }
    }
}
