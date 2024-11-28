package me.kinhiro.jek.gradle.model

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlChildrenName
import nl.adaptivity.xmlutil.serialization.XmlElement

@Serializable
data class MavenMetadata(
    @XmlElement val groupId: String,
    @XmlElement val artifactId: String,
    @XmlElement val version: String?,
    @XmlElement(false) val modelVersion: String?,
    @XmlElement val versioning: Versioning?
) {
    @Serializable
    data class Versioning(
        @XmlElement val latest: String,
        @XmlElement val release: String?,
        @XmlChildrenName("version") val versions: List<String>
    )
}
