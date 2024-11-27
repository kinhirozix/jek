package me.kinhiro.jek.gradle.plugin

import me.kinhiro.jek.gradle.Constants.Constraints
import me.kinhiro.jek.gradle.Constants.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.PluginInstantiationException
import org.gradle.util.GradleVersion

internal fun Project.checkSupportedVersion() {
    if (GradleVersion.current() < Constraints.MINIMAL_GRADLE_VERSION) throw PluginInstantiationException(
        "${Plugin.NAME} requires Gradle ${Constraints.MINIMAL_GRADLE_VERSION} and higher"
    )
}
