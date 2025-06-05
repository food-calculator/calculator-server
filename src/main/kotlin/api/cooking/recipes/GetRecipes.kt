package de.fridolin1.api.cooking.recipes

import de.fridolin1.models.cooking.Recipe
import de.fridolin1.models.snippets.toRecipeHead
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getRecipes() {
    get {
        DatabaseManager.query {
            call.respond(Recipe.all().map { it.toRecipeHead() })
        }
    }
}