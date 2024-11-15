plugins {
    `maven-publish`
}

base.archivesName = "jek-compiler-plugin"
description = "Just Enough Kinhiro (KCP)"

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

publishing {
    repositories {
        mavenLocal()
    }

    publications {
        create<MavenPublication>("jekKotlinCompilerPluginMaven") {
            groupId = project.group.toString()
            artifactId = project.base.archivesName.get()
            version = project.version.toString()
            description = project.description
            from(components["java"])
        }
    }
}
