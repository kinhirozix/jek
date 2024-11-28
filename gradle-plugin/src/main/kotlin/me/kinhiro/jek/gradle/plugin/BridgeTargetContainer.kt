package me.kinhiro.jek.gradle.plugin

import org.gradle.api.NamedDomainObjectCollection

@BridgeTargetsDsl
interface BridgeTargetContainer {
    val targets: NamedDomainObjectCollection<BridgeTarget>
}
