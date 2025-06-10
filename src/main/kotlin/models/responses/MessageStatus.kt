package de.fridolin1.models.responses

import kotlinx.serialization.Serializable

@Serializable
enum class MessageStatus {
    SUCCESS, ERROR, DEPENDENCIES_NOT_EXISTING, MISSING_INFORMATION
}