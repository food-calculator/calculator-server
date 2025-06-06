package de.fridolin1.models.images

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

class Image(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Image>(Images)

    var fileID by Images.fileID
    var contentType by Images.contentType
}

object Images: IntIdTable("Images") {
    val fileID = long("imageID")
    val contentType = varchar("contentType", 16)
}