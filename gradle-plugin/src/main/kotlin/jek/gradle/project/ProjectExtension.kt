package jek.gradle.project

import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class ProjectExtension @Inject constructor(
    private val objects: ObjectFactory,
    private val project: Project
) : ExtensionAware {
    val projectType: Property<ProjectType> get() = objects.property(ProjectType::class.java)
}
