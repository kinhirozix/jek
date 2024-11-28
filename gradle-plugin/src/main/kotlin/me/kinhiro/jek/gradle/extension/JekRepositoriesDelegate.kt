package me.kinhiro.jek.gradle.extension

import me.kinhiro.jek.gradle.GradleProperties
import me.kinhiro.jek.gradle.gradleProperty
import org.gradle.api.Action
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ProviderFactory
import java.net.URI

class JekRepositoriesDelegate(
    private val objects: ObjectFactory,
    private val repositories: RepositoryHandler,
    private val providers: ProviderFactory
) {
    internal fun createMavenRepository(
        name: String,
        url: String,
        urlWithGithubPersonal: String = url,
        action: Action<MavenArtifactRepository> = Action {}
    ): MavenArtifactRepository = providers.gradleProperty(GradleProperties.UseGithubPersonal).map { useGithubPersonal ->
        repositories.maven { repo ->
            repo.name = name
            repo.url = URI.create(if (useGithubPersonal) url else urlWithGithubPersonal)
            action.execute(repo)
        }
    }.getOrElse(repositories.mavenLocal())
}
