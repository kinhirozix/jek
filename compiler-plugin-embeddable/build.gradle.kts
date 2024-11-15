plugins {
    `maven-publish`
}

base.archivesName = "jek-compiler-plugin-embeddable"
description = "Just Enough Kinhiro (KCP-E)"

publishing {
    repositories {
        mavenLocal()
    }

    publications {
        create<MavenPublication>("jekCompilerPluginEmbeddableMaven") {
            groupId = project.group.toString()
            artifactId = project.base.archivesName.get()
            version = project.version.toString()
            description = project.description
            from(components["java"])
        }
    }
}
