package jek.gradle.internal

import jek.gradle.JekExtension
import jek.gradle.project.ProjectExtension
import jek.gradle.publish.PublishExtension

internal val JekExtension.projectExtension: ProjectExtension
    get() = extensions.getByType(ProjectExtension::class.java)

internal val JekExtension.publishExtension: PublishExtension
    get() = extensions.getByType(PublishExtension::class.java)
