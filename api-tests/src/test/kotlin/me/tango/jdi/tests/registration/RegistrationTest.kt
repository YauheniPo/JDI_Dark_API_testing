package jdi.tests.registration

import components.component.service.registration.RegistrationService
import components.data.Generator
import jdi.tests.BaseTest
import jdi.TestGroup.Companion.HEALTH_CHECK
import jdi.TestGroup.Companion.REGRESSION
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import facilitator.client.proto.v9.RegistrationV9Protos as Proto

class RegistrationTest : BaseTest() {

    @Test(
        groups = [REGRESSION, HEALTH_CHECK],
        description = "Validate user registration.")
    fun registrationTest() {
        val tangoRegistrationRequest = RegistrationService().generateTangoRegistrationRequest(
            phoneNumber = Generator.generatePhoneNumber("+7")
        )

        val registrationResponseProto: Proto.TangoRegistrationResponse = userSteps.register(tangoRegistrationRequest)

        assertThat(registrationResponseProto.socialIdStatus.originalPrimaryNumber)
            .isEqualTo(tangoRegistrationRequest.contact.phoneNumber.subscriberNumber)
    }
}
