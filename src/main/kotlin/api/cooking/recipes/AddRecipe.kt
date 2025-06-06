package de.fridolin1.api.cooking.recipes

import de.fridolin1.models.cooking.Ingredient
import de.fridolin1.models.cooking.Recipe
import de.fridolin1.models.cooking.RecipeIngredient
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.models.snippets.RawRecipe
import de.fridolin1.models.snippets.toRawRecipe
import de.fridolin1.modules.DatabaseManager
import de.fridolin1.utils.checkIngredients
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.addRecipe() {
    post {
        val recipe = call.receive<RawRecipe>()
        val ingredients = recipe.ingredients

        val allIngredientsExists = checkIngredients(ingredients)
        if (!allIngredientsExists) {
            call.respond(Message(MessageStatus.ERROR, "Following ingredients not found: $allIngredientsExists"))
            return@post
        }

        DatabaseManager.query {
            val recipe = Recipe.new {
                this.name = recipe.name
                this.description = recipe.description
                this.minimumAge = recipe.minimumAge
                this.timeExpenditure = recipe.timeExpenditure
            }
            ingredients.forEach { ingredient ->
                RecipeIngredient.new {
                    this.recipe = recipe
                    this.quantity = ingredient.quantity
                    this.unit = ingredient.unit
                    this.ingredient = Ingredient[ingredient.ingredientID]
                }
            }
            call.respond(Message(MessageStatus.SUCCESS, recipe.toRawRecipe()))
        }
    }
}