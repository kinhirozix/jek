package me.kinhiro.jek.gradle.extension

import me.kinhiro.jek.gradle.JekConstants.Extensions
import me.kinhiro.jek.gradle.JekConstants.Repositories
import me.kinhiro.jek.gradle.JustEnoughKinhiroDsl
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
            Repositories.Names.KINHIRO_RELEASES,
            Repositories.KINHIRO_RELEASES,
            Repositories.GITHUB_PERSONAL,
            action
        )

    @JvmOverloads
    fun snapshots(action: Action<MavenArtifactRepository> = Action {}): MavenArtifactRepository =
        delegate.createMavenRepository(
            Repositories.Names.KINHIRO_SNAPSHOTS,
            Repositories.KINHIRO_SNAPSHOTS,
            Repositories.GITHUB_PERSONAL,
            action
        )

    @JvmOverloads
    fun githubPersonal(action: Action<MavenArtifactRepository> = Action {}): MavenArtifactRepository =
        delegate.createMavenRepository(
            Repositories.Names.GITHUB_PERSONAL,
            Repositories.GITHUB_PERSONAL,
            Repositories.GITHUB_PERSONAL,
            action
        )

    fun allinRepositories() {
        releases()
        snapshots()
        githubPersonal()
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
