package com.kinhiro.composite

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

abstract class AbstractSettings : Plugin<Settings> {
    abstract fun Settings.applyPlugin()
    final override fun apply(target: Settings) = target.applyPlugin()
}
