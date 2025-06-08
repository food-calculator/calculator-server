package de.fridolin1.api.cooking.recipes

import de.fridolin1.models.cooking.Ingredient
import de.fridolin1.models.cooking.Recipe
import de.fridolin1.models.cooking.RecipeIngredient
import de.fridolin1.models.images.Image
import de.fridolin1.models.images.RecipeImage
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.models.snippets.RawRecipe
import de.fridolin1.models.snippets.toRawRecipe
import de.fridolin1.modules.DatabaseManager
import de.fridolin1.utils.checkImages
import de.fridolin1.utils.checkIngredients
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.createRecipe() {
    post {
        val recipe = call.receive<RawRecipe>()
        val ingredients = recipe.ingredients
        val images = recipe.images

        val allIngredientsExists = checkIngredients(ingredients)
        if (!allIngredientsExists) {
            call.respond(Message(MessageStatus.ERROR, "Cant ind all Ingredients"))
            return@post
        }

        val allImagesExists = checkImages(images)
        if (!allImagesExists) {
            call.respond(Message(MessageStatus.DEPENDENCIES_NOT_EXISTING, "Cant find all Images"))
            return@post
        }

        DatabaseManager.query {
            val recipe = Recipe.new {
                this.name = recipe.name
                this.description = recipe.description
                this.minimumAge = recipe.minimumAge
                this.timeExpenditure = recipe.timeExpenditure
                this.portionSize = recipe.portionSize
            }
            ingredients.forEach { it ->
                RecipeIngredient.new {
                    this.recipe = recipe
                    this.quantity = it.quantity
                    this.ingredient = Ingredient[it.ingredientID]
                }
            }
            images.forEach {
                RecipeImage.new {
                    this.recipe = recipe
                    this.image = Image[it]
                }
            }
            call.respond(Message(MessageStatus.SUCCESS, recipe.toRawRecipe()))
        }
    }
}