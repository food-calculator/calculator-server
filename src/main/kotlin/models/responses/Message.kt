package de.fridolin1.models.responses

import kotlinx.serialization.Serializable

@Serializable
data class Message<T>(
    val status: MessageStatus,
    val message: T
)
