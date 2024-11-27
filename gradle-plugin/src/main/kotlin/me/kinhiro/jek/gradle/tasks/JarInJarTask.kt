package me.kinhiro.jek.gradle.tasks

import me.kinhiro.jek.gradle.Constants.Descriptions
import me.kinhiro.jek.gradle.Constants.Tasks
import org.gradle.api.Project
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.jvm.tasks.Jar

abstract class JarInJarTask : Jar() {
    init {
        group = Tasks.GROUP_NAME
        description = Descriptions.JAR_IN_JAR
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    companion object : Registrable {
        override fun register(project: Project) = project.registerTask<JarInJarTask>(Tasks.JAR_IN_JAR) {
            archiveBaseName.convention(project.name)
        }
    }
}
