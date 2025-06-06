package de.fridolin1.api.cooking.ingredients

import de.fridolin1.models.cooking.Ingredient
import de.fridolin1.models.cooking.toDTO
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.addIngredient() {
    post {
        val name = call.receiveParameters()["name"]!!
        DatabaseManager.query {
            val ingredient = Ingredient.new {
                this.name = name
            }
            call.respond(Message(MessageStatus.SUCCESS, ingredient.toDTO()))
        }
    }
}