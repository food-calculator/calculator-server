package de.fridolin1.api.cooking.recipes

import de.fridolin1.models.cooking.Recipe
import de.fridolin1.models.snippets.toRecipeHead
import de.fridolin1.utils.fromBase64
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.searchRecipes() {
    get {
        val name = call.request.queryParameters["name"]?.fromBase64() ?: ""
        val ingredients = call.request.queryParameters["ingredients"]
            ?.fromBase64()?.split(", ")?.map { it.toInt() } ?: emptyList()
        val minAge = call.request.queryParameters["minAge"]?.fromBase64()?.toInt() ?: 0

        // @TODO databaseManager.query
        call.respond(
            Recipe.all()
                .filter { it.name.startsWith(name) }
                .filter { recipe -> recipe.recipesIngredients.map { it.ingredient.id.value }.containsAll(ingredients) }
                .filter { it.minimumAge >= minAge }
                .map { it.toRecipeHead() }
        )
    }
}