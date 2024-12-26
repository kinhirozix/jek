package com.kinhiro.jek.gradle.extension

import com.kinhiro.jek.gradle.JekConstants.Extensions
import com.kinhiro.jek.gradle.JustEnoughKinhiroDsl
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.model.ObjectFactory
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.ProviderFactory
import java.io.File
import javax.inject.Inject

@JustEnoughKinhiroDsl
abstract class JekRepositoriesExtension @Inject constructor(
    private val objects: ObjectFactory,
    private val repositories: RepositoryHandler,
    private val providers: ProviderFactory,
    private val rootDir: File
) : ExtensionAware {
    private val delegate: JekRepositoriesDelegate = JekRepositoriesDelegate(objects, repositories, providers)

    @JvmOverloads
    fun releases(action: Action<MavenArtifactRepository> = Action {}): MavenArtifactRepository =
        delegate.createMavenRepository(
            "MavenReleases",
            "https://maven.kinhiro.com/releases",
            action = action
        )

    @JvmOverloads
    fun snapshots(action: Action<MavenArtifactRepository> = Action {}): MavenArtifactRepository =
        delegate.createMavenRepository(
            "MavenSnapshots",
            "https://maven.kinhiro.com/snapshots",
            action = action
        )

    @JvmOverloads
    fun mavenPersonal(action: Action<MavenArtifactRepository> = Action {}): MavenArtifactRepository =
        delegate.createMavenRepository(
            "MavenPersonal",
            "https://kinhirozix.github.io/maven",
            action = action
        )

    fun defaultRepositories() {
        releases()
        snapshots()
        mavenPersonal()
    }

    companion object : Registrable<JekRepositoriesExtension> {
        override fun register(project: Project, target: Any): JekRepositoriesExtension = target.configureExtension(
            Extensions.JEK,
            project.repositories,
            project.providers,
            project.rootDir
        )
    }
}
