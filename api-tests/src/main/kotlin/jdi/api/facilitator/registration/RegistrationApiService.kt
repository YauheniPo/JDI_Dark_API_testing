package jdi.api.facilitator.registration

import com.epam.http.annotations.*
import com.epam.http.requests.RestMethod
import com.google.common.net.HttpHeaders
import jdi.api.facilitator.FacilitatorKuberPod
import jdi.api.facilitator.FacilitatorKuberPod.Companion.FACILITATOR_KUBER_POD_URI


@ServiceDomain(FACILITATOR_KUBER_POD_URI)
class RegistrationApiService: FacilitatorKuberPod() {

    companion object {
        private const val USER_REGISTRATION_REST = "register.proto"

//        @QueryParameter(name = "param1", value = "test")
//        @Cookie(name = "session_id", value = "1234")
//        @FormParameter(name = "some1", value = "one")
//        @MultiPart(controlName = "file", fileName = "myFile")
//        @Proxy(host = "127.0.0.1", port = 8888, scheme = "http")
//        @URL("http://www.google.se")
//        @GET(value = "503")  @RetryOnFailure(numberOfRetryAttempts = 2, delay = 1, unit = TimeUnit.SECONDS)  @IgnoreRetry
        @Headers(
            Header(name = HttpHeaders.ACCEPT, value = "application/x-protobuf"),
            Header(name = HttpHeaders.CONTENT_TYPE, value = "application/x-protobuf")  //@ContentType(JSON)
        )
        @POST(USER_REGISTRATION_REST)  //@GET("/{firstName}/{lastName}") @PUT("/put") @PATCH("/patch") @DELETE("/delete")
        lateinit var register: RestMethod
    }
}
