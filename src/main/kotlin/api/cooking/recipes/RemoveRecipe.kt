package de.fridolin1.api.cooking.recipes

import de.fridolin1.models.cooking.Recipe
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.request.*
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.removeRecipe() {
    delete {
        val id = call.receiveParameters()["id"]?.toInt()!!
        DatabaseManager.query {
            val recipe = Recipe[id]
            recipe.recipesIngredients.forEach { it.delete() }
            recipe.images.forEach { it.delete() }
            recipe.delete()
        }
        call.respond(Message(MessageStatus.SUCCESS, "Successfully delete Entry with id: $id"))
    }
}