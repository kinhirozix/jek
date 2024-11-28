package me.kinhiro.jek.gradle.model

import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.kinhiro.jek.gradle.JekConstants.Links
import me.kinhiro.jek.gradle.JekConstants.Repositories
import me.kinhiro.jek.gradle.util.Logger
import nl.adaptivity.xmlutil.serialization.XML
import java.io.InputStream
import java.net.URI
import java.net.URL
import java.nio.file.Path
import kotlin.io.path.readText

internal fun Coordinates.resolveLatestVersion(repositoryUrl: String = Repositories.GITHUB_PERSONAL): String? {
    val host = repositoryUrl.trimEnd('/')
    val path = toString().replace(':', '/').replace('.', '/')
    val url = URI.create("$host/$path/maven-metadata.xml").toURL()
    return decode<MavenMetadata>(url).versioning?.latest
}

internal inline fun <reified T> decode(url: URL): T = decode<T>(url.openStream())
internal inline fun <reified T> decode(path: Path): T = decode(path.readText(Charsets.UTF_8))
internal inline fun <reified T> decode(input: InputStream): T =
    decode<T>(input.bufferedReader(Charsets.UTF_8).use { reader -> reader.readText() })

internal inline fun <reified T> decode(input: String, format: StringFormat = obtainStringFormat<T>()): T =
    runCatching { format.decodeFromString<T>(input) }.onFailure { exception ->
        Logger(T::class.java).error(
            """
                Cannot parse the provided input as ${T::class.java.name}.
                Please file an issue attaching the content and exception message to: ${Links.GITHUB_REPOSITORY}/issues/new
                
                ## Model:
                ```
                ${T::class.java.name}
                ```
                
                ## Content:
                ```
                ${input.replaceIndent("                ").trimStart()}
                ```
                
                ## Exception:
                ```
                ${exception.stackTraceToString().replaceIndent("                ").trimStart()}
                ```
            """.trimIndent()
        )
    }.getOrThrow()

private val json: Json = Json {}
private val xml: XML = XML {}

private inline fun <reified T> obtainStringFormat(): StringFormat = when (T::class) {
    MavenMetadata::class -> xml
    else -> throw IllegalArgumentException("unknown type ${T::class.java.name}.")
}
