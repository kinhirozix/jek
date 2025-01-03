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
    val enabled: Property<Boolean> get() = objects.property(Boolean::class.java)
    val debug: Property<Boolean> get() = objects.property(Boolean::class.java)

    @JvmOverloads
    fun java(action: Action<JavaBridgeExtension> = Action {}) {
    }

    @JvmOverloads
    fun kotlin(action: Action<KotlinBridgeExtension> = Action {}) {
    }

    companion object : Registrable<BridgeExtension> {
        @JvmStatic
        override fun register(project: Project, target: Any): BridgeExtension = target.configureExtension(
            Extensions.BRIDGE
        ) {
            enabled.convention(true)
            debug.convention(false)
        }
    }
}
