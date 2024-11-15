plugins {
    `java-gradle-plugin`
    `maven-publish`
}

base.archivesName = "jek-gradle-plugin"
description = "Just Enough Kinhiro (GP)"

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(project(":compiler-plugin"))
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        create("jekGradlePlugin") {
            id = "me.kinhiro.jek"
            implementationClass = "jek.gradle.JekPlugin"
            displayName = "Just Enough Kinhiro"
            description = project.description
        }
    }
}

publishing {
    repositories {
        mavenLocal()
    }

    publications {
        create<MavenPublication>("jekGradlePluginMaven") {
            groupId = project.group.toString()
            artifactId = project.base.archivesName.get()
            version = project.version.toString()
            description = project.description
            from(components["java"])
        }
    }
}
