package de.fridolin1.api.images

import de.fridolin1.models.images.Image
import de.fridolin1.modules.DatabaseManager
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.getImage() {
    get {
        val imageID = call.queryParameters["id"]?.toInt()!!
        DatabaseManager.query {
            val file = Image[imageID]
            call.response.header(HttpHeaders.ContentType, file.contentType)
            call.respondFile(File("./images/${file.fileID}"))
        }
    }
}