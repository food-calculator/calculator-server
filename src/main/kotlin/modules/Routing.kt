package de.fridolin1.modules

import de.fridolin1.api.apiRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
            call.respondText("Not Found", status = HttpStatusCode.NotFound)
        }
        exception<Throwable> { call, cause ->
            cause.printStackTrace()
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        route("/api") { apiRoutes() }
    }
}
