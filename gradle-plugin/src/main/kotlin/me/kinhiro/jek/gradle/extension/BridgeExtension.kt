package me.kinhiro.jek.gradle.extension

import me.kinhiro.jek.gradle.Constants.Extensions
import me.kinhiro.jek.gradle.JustEnoughKinhiro
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Property
import javax.inject.Inject

@JustEnoughKinhiro
abstract class BridgeExtension @Inject constructor(
    private val objects: ObjectFactory
) : ExtensionAware {
    val enabled: Property<Boolean> get() = objects.property(Boolean::class.java)
    val debug: Property<Boolean> get() = objects.property(Boolean::class.java)
    val type: Property<BridgeType> get() = objects.property(BridgeType::class.java)

    companion object : Registrable<BridgeExtension> {
        override fun register(project: Project, target: Any): BridgeExtension = target.configureExtension(
            Extensions.BRIDGE
        ) {
            enabled.convention(true)
            debug.convention(false)
            val isCommon = project.path.equals(":common", true)
            val isShared = project.path.equals(":shared", true)
            if (isCommon || isShared) type.convention(BridgeType.EXPECT)
        }
    }
}
