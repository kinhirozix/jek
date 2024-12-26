package com.kinhiro.jek.gradle.plugin

import org.gradle.api.Action
import org.gradle.plugins.ide.idea.model.IdeaModel
import org.gradle.tooling.model.idea.IdeaModule

internal fun IdeaModel.module(action: IdeaModule.() -> Unit) = module(Action { action })
