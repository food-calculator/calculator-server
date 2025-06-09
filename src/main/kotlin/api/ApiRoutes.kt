package de.fridolin1.api

import de.fridolin1.api.cooking.ingredients.addIngredient
import de.fridolin1.api.cooking.ingredients.getIngredients
import de.fridolin1.api.cooking.ingredients.removeIngredient
import de.fridolin1.api.cooking.recipes.*
import de.fridolin1.api.images.getImage
import de.fridolin1.api.images.imageUpload
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.apiRoutes() {
    route("ingredients") {
        route("list") { getIngredients() }
        route("add") { addIngredient() }
        route("remove") { removeIngredient() }
    }
    route("recipes") {
        route("list") { getRecipes() }
        route("search") { searchRecipes() }
        route("get") { getRecipe() }
        route("create") { createRecipe() }
        route("update") { updateRecipe() }
        route("remove") { removeRecipe() }
    }
    route("images") {
        route("upload") { imageUpload() }
        route("get") { getImage() }
    }

    get {
        call.respond(Message(MessageStatus.SUCCESS, "Hello API! This is the Server for the food calculator"))
    }
}