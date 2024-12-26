package com.kinhiro.jek.gradle.extension

import com.kinhiro.jek.gradle.JekConstants.Extensions
import com.kinhiro.jek.gradle.JustEnoughKinhiroDsl
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Property
import javax.inject.Inject

@JustEnoughKinhiroDsl
abstract class BridgeExtension @Inject constructor(
    private val objects: ObjectFactory
) : ExtensionAware {
    private var isJava: Boolean = false
    private var isKotlin: Boolean = false

    val enabled: Property<Boolean> get() = objects.property(Boolean::class.java)
    val debug: Property<Boolean> get() = objects.property(Boolean::class.java)

    fun java(action: Action<Unit>) {
        isJava = true
    }

    fun kotlin(action: Action<Unit>) {
        isKotlin = true
    }

    companion object : Registrable<BridgeExtension> {
        override fun register(project: Project, target: Any): BridgeExtension = target.configureExtension(
            Extensions.BRIDGE
        ) {
            enabled.convention(true)
            debug.convention(false)
        }
    }
}
