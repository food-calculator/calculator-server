package de.fridolin1.api.cooking.ingredients

import de.fridolin1.models.cooking.Ingredient
import de.fridolin1.models.cooking.RecipeIngredient
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.removeIngredient() {
    delete {
        val id = call.receiveParameters()["id"]?.toInt()!!
        DatabaseManager.query {
            val recipes = RecipeIngredient.all().filter { it.ingredient.id.value == id }.map { it.recipe }
            if (recipes.isNotEmpty()) {
                call.respond(Message(MessageStatus.DEPENDENCIES_NOT_EXISTING, recipes))
                return@query
            }

            Ingredient[id].delete()
            call.respond(Message(MessageStatus.SUCCESS, "Deleted ingredient with id $id"))
        }
    }
}