package me.kinhiro.jek.gradle.tasks

import org.gradle.api.Project
import org.gradle.api.Task

internal interface Registrable {
    fun register(project: Project)
}

internal inline fun <reified T : Task> Project.registerTask(
    vararg names: String,
    configureWithType: Boolean = true,
    noinline configuration: T.() -> Unit = {}
) {
    for (name in names) tasks.maybeCreate(name, T::class.java)
    when (configureWithType) {
        true -> tasks.withType(T::class.java, configuration)
        false -> for (name in names) tasks.named(name, T::class.java).configure(configuration)
    }
}
