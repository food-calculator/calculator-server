package de.fridolin1.modules

import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun Application.enableCors() {
    install(CORS) {
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
        anyMethod()
    }
//    routing {
//        openAPI(path = "openapi") @TODO configure the openAPI
//    }
}
