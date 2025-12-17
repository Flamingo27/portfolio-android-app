package com.alokparna.portfolio.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable

@Serializable
data class ContactRequestBody(val name: String, val email: String, val message: String)

object ContactRepository {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun sendContactMessage(name: String, email: String, message: String): Boolean {
        return try {
            val response = client.post("https://portfolio-alokparna.pages.dev/contact") {
                contentType(ContentType.Application.Json)
                setBody(ContactRequestBody(name, email, message))
            }
            response.status.value in 200..299
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}