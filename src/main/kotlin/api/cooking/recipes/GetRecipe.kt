package de.fridolin1.api.cooking.recipes

import de.fridolin1.models.cooking.Recipe
import de.fridolin1.models.snippets.toRawRecipe
import de.fridolin1.modules.DatabaseManager
import de.fridolin1.utils.fromBase64
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.getRecipe() {
    get {
        val id = call.request.queryParameters["id"]?.fromBase64()?.toInt()!!
        DatabaseManager.query {
            call.respond(Recipe[id].toRawRecipe())
        }
    }
}