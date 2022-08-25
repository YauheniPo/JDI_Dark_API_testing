package jdi.tests.user_info

import profilerator.proto.v3.UserProfileV3Protos
import components.data.Generator
import jdi.tests.BaseTest
import jdi.TestGroup.Companion.HEALTH_CHECK
import jdi.TestGroup.Companion.REGRESSION
import org.assertj.core.api.Assertions
import org.testng.annotations.Test
import facilitator.client.proto.v9.RegistrationV9Protos as Proto

class UserInfoTest : BaseTest() {

    @Test(
        groups = [REGRESSION, HEALTH_CHECK],
        description = "Validate user info.")
    fun userInfoTest() {
        val tangoRegistrationRequest = RegistrationService().generateTangoRegistrationRequest(
            phoneNumber = Generator.generatePhoneNumber("+7")
        )

        val registrationResponseProto: Proto.TangoRegistrationResponse = userSteps.register(tangoRegistrationRequest)

        val userProfileRequest = UserProfileV3Protos.UserProfileRequest.newBuilder().apply {
            addViewees(registrationResponseProto.accountId)
            addQuerySetting(UserProfileV3Protos.QuerySetting.newBuilder().apply {
                type = UserProfileV3Protos.DataType.basic
            })
        }.build()

        val userInfoResponse = userSteps.getUserInfo(userProfileRequest, CryptoUtils.symetricDecryptToLong(registrationResponseProto.accountId).toString())
        Assertions.assertThat(registrationResponseProto.accountId)
            .isEqualTo(userInfoResponse.vieweeEntriesList.first().viewee)
    }
}
