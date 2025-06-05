package de.fridolin1.api.cooking.recipes

import de.fridolin1.models.cooking.Recipe
import de.fridolin1.models.snippets.toRecipeHead
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getRecipes() {
    get {
        // @TODO databaseManager.query
        call.respond(Recipe.all().map { it.toRecipeHead() })
    }
}