package de.fridolin1.models.snippets

import de.fridolin1.models.images.Image
import kotlinx.serialization.Serializable

@Serializable
data class ImageSnippet(
    val id: Int
)

fun Image.toSnippet(): ImageSnippet = ImageSnippet(this.id.value)