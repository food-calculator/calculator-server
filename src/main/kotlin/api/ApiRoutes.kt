package de.fridolin1.api

import de.fridolin1.api.cooking.ingredients.getIngredients
import de.fridolin1.api.cooking.recipes.getRecipe
import de.fridolin1.api.cooking.recipes.getRecipes
import de.fridolin1.api.cooking.recipes.searchRecipes
import io.ktor.server.routing.*

fun Route.apiRoutes() {
    route("ingredients") {
        route("list") { getIngredients() }
    }
    route("recipes") {
        route("list") { getRecipes() }
        route("search") { searchRecipes() }
        route("get") { getRecipe() }
    }
}