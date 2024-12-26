package com.kinhiro.jek.gradle.util

import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

private val STACK_WALKER: StackWalker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)

internal fun logger(): Logger {
    var caller = STACK_WALKER.callerClass
    val enclosing = caller.enclosingClass
    if (enclosing != null && caller.kotlin.isCompanion) caller = enclosing
    return Logging.getLogger(caller)
}
