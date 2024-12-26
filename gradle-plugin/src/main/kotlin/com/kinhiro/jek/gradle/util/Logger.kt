package com.kinhiro.jek.gradle.util

import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

class Logger(cls: Class<*>) {
    private val logger: Logger = Logging.getLogger(cls)
    private val prefix: String = "[JEK]"

    fun debug(message: String) = logger.debug("$prefix $message")
    fun debug(message: String, vararg objects: Any) = logger.debug("$prefix $message", *objects)
    fun debug(message: String, throwable: Throwable) = logger.debug("$prefix $message", throwable)
    fun info(message: String) = logger.info("$prefix $message")
    fun info(message: String, vararg objects: Any) = logger.info("$prefix $message", *objects)
    fun info(message: String, throwable: Throwable) = logger.info("$prefix $message", throwable)
    fun lifecycle(message: String) = logger.lifecycle("$prefix $message")
    fun lifecycle(message: String, vararg objects: Any) = logger.lifecycle("$prefix $message", *objects)
    fun lifecycle(message: String, throwable: Throwable) = logger.lifecycle("$prefix $message", throwable)
    fun warn(message: String) = logger.warn("$prefix $message")
    fun warn(message: String, vararg objects: Any) = logger.warn("$prefix $message", *objects)
    fun warn(message: String, throwable: Throwable) = logger.warn("$prefix $message", throwable)
    fun quiet(message: String) = logger.quiet("$prefix $message")
    fun quiet(message: String, vararg objects: Any) = logger.quiet("$prefix $message", *objects)
    fun quiet(message: String, throwable: Throwable) = logger.info("$prefix $message", throwable)
    fun error(message: String) = logger.error("$prefix $message")
    fun error(message: String, vararg objects: Any) = logger.error("$prefix $message", *objects)
    fun error(message: String, throwable: Throwable) = logger.error("$prefix $message", throwable)
}
