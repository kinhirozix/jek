package jek.gradle.internal

import jek.gradle.JekExtension
import jek.gradle.project.ProjectExtension
import jek.gradle.publish.PublishExtension
import org.gradle.api.Project

internal val Project.jekExtension: JekExtension
    get() = extensions.findByType(JekExtension::class.java)
        ?: error("The Just Enough Kinhiro (JEK) Gradle plugin is not applied to this project.")

internal val Project.projectExtension: ProjectExtension
    get() = jekExtension.extensions.getByType(ProjectExtension::class.java)

internal val Project.publishExtension: PublishExtension
    get() = extensions.getByType(PublishExtension::class.java)
