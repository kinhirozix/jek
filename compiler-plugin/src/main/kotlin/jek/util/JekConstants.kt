package jek.util

object JekConstants {
    const val PLUGIN_ID: String = "jek-compiler-plugin"
    val PLUGIN_VERSION: String = JekConstants::class.java.`package`.implementationVersion ?: "0.0.1"
}
