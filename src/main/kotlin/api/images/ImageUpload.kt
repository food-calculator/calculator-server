package de.fridolin1.api.images

import de.fridolin1.models.images.Image
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.models.snippets.toSnippet
import de.fridolin1.modules.DatabaseManager
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.cio.*
import io.ktor.utils.io.*
import java.io.File
import javax.imageio.ImageIO

fun Route.imageUpload() {
    post {
        println(call.request.headers)
        val fileID = System.nanoTime()
        val contentType = call.request.headers[HttpHeaders.ContentType]!!
        println(contentType)
        val file = File("./images/${fileID}")
        if (!file.parentFile.exists()) file.parentFile.mkdir()
        call.receiveChannel().copyAndClose(file.writeChannel())
        if (ImageIO.read(file) == null) {
            file.delete()
            call.respond(Message(MessageStatus.ERROR, "Unknown image type"))
            return@post
        }
        DatabaseManager.query {
            val image = Image.new {
                this.fileID = fileID
                this.contentType = contentType
            }
            call.respond(Message(MessageStatus.SUCCESS, image.toSnippet()))
        }
    }
}