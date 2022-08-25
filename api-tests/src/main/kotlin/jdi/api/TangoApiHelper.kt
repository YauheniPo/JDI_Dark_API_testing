package jdi.api

import io.restassured.RestAssured
import io.restassured.config.EncoderConfig
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import components.data.Generator
import org.springframework.http.HttpHeaders

class TangoApiHelper {

    fun getRestAssuredRequestSpecification(id: String = Generator.randomUid()): RequestSpecification = RestAssured.given()
        .headers(getAuthHeader(id))
        .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig()
            .encodeContentTypeAs("application/x-protobuf", ContentType.JSON))
        )

    private fun getAuthHeader(id: String, device: String? = null): HttpHeaders {
        return HttpHeaders().apply {
            add("***", id)
            device?.let { add("*****", it) }
        }
    }
}
