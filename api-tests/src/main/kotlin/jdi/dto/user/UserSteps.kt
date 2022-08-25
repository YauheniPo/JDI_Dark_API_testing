package jdi.dto.user

import com.epam.http.response.RestResponse
import profilerator.proto.v3.UserProfileV3Protos
import jdi.api.TangoApiHelper
import jdi.api.facilitator.registration.RegistrationApiService
import jdi.api.profilerator.user_info.UserInfoApiService
import jdi.dto.BaseStep
import org.hamcrest.Matchers.equalTo
import facilitator.client.proto.v9.RegistrationV9Protos as Proto


class UserSteps : BaseStep() {

    fun register(registrationRequest: Proto.TangoRegistrationRequest): Proto.TangoRegistrationResponse {
        val registrationResponse: RestResponse =
            RegistrationApiService.register.call(TangoApiHelper().getRestAssuredRequestSpecification().body(registrationRequest.toByteArray()))
        registrationResponse.isOk //.hasErrors() .validate() ....
            .assertThat()
            .statusCode(equalTo(200)) // .body("path", equalTo("something")) all response items (using hamcrest Matchers)
        return Proto.TangoRegistrationResponse.parseFrom(registrationResponse.body.byteInputStream()).also {
            log.info("Raw registration response data: ${it.allFields}")
        }
    }

    fun getUserInfo(userProfileRequest: UserProfileV3Protos.UserProfileRequest, encryptedAccountId: String): UserProfileV3Protos.UserProfileResponse {
        val userInfoResponse = UserInfoApiService.userInfo
            .call(TangoApiHelper().getRestAssuredRequestSpecification(encryptedAccountId)
                .body(userProfileRequest.toByteArray()))
        userInfoResponse.isOk
            .assertThat()
            .statusCode(equalTo(200))
        return UserProfileV3Protos.UserProfileResponse.parseFrom(userInfoResponse.body.byteInputStream()).also {
            log.info("Raw registration response data: ${it.allFields}")
        }
    }
}
