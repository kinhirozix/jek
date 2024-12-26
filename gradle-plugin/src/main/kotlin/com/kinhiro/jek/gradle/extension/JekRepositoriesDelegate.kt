package com.kinhiro.jek.gradle.extension

import me.kinhiro.jek.gradle.JekConstants.GradleProperties
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
        urlWithPersonalRepository: String = url,
        action: Action<MavenArtifactRepository> = Action {}
    ): MavenArtifactRepository = providers.gradleProperty(GradleProperties.USE_PERSONAL_REPOSITORY)
        .map { usePersonalRepository ->
            repositories.maven { repo ->
                repo.name = name
                repo.url = URI.create(if (usePersonalRepository.toBoolean()) url else urlWithPersonalRepository)
                action.execute(repo)
            }
        }.getOrElse(repositories.mavenLocal())
}
