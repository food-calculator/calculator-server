package de.fridolin1.utils

import de.fridolin1.models.images.Image
import de.fridolin1.modules.DatabaseManager

suspend fun checkImages(images: List<Int>): Boolean {
    var isEmpty = false
    DatabaseManager.query {
        val existingImageIDs = Image.all().map { it.id.value }
        isEmpty = images.none { !existingImageIDs.contains(it) }
    }
    return isEmpty
}