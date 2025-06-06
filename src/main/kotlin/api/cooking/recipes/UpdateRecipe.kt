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

fun Route.updateRecipe() {
    put {
        val recipeData = call.receive<RawRecipe>()
        val ingredients = recipeData.ingredients
        val images = recipeData.images

        val allIngredientsExists = checkIngredients(ingredients)
        if (!allIngredientsExists) {
            call.respond(Message(MessageStatus.DEPENDENCIES_NOT_EXISTING, "Cant fine all Ingredients"))
            return@put
        }

        val allImagesExists = checkImages(images)
        if (!allImagesExists) {
            call.respond(Message(MessageStatus.DEPENDENCIES_NOT_EXISTING, "Cant find all Images"))
            return@put
        }

        DatabaseManager.query {
            if (Recipe.all().none { it.id.value == recipeData.id }) {
                call.respond(Message(MessageStatus.ERROR, "Recipe not found with id ${recipeData.id}"))
                return@query
            }
            val recipe = Recipe[recipeData.id]
            recipe.name = recipeData.name
            recipe.description = recipeData.description
            recipe.minimumAge = recipeData.minimumAge
            recipe.timeExpenditure = recipeData.timeExpenditure
            recipe.recipesIngredients.forEach { it.delete() }
            ingredients.forEach {
                RecipeIngredient.new {
                    this.recipe = recipe
                    this.quantity = it.quantity
                    this.unit = it.unit
                    this.ingredient = Ingredient[it.ingredientID]
                }
            }
            recipe.images.forEach { it.delete() }
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