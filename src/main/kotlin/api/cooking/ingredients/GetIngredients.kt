package de.fridolin1.api.cooking.ingredients

import de.fridolin1.models.cooking.Ingredient
import de.fridolin1.models.cooking.toDTO
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getIngredients() {
    get {
        // @TODO databaseManager.query
        call.respond(Ingredient.all().map { it.toDTO() })
    }
}