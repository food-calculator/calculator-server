package de.fridolin1

import de.fridolin1.modules.enableCors
import de.fridolin1.modules.configureMonitoring
import de.fridolin1.modules.configureRouting
import de.fridolin1.utils.json
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    enableCors()
    configureMonitoring()
    configureRouting()
    install(ContentNegotiation) {
        json(json)
    }
}
