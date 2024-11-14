plugins {
    `maven-publish`
}

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
