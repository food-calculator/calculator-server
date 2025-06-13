package de.fridolin1.modules

import io.ktor.http.HttpHeaders
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun Application.enableCors() {
    install(CORS) {
        allowCredentials = true
        anyHost()
        anyMethod()
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType)
    }
//    routing {
//        openAPI(path = "openapi") @TODO configure the openAPI
//    }
}
