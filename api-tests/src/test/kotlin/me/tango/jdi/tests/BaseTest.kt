package jdi.tests

import com.epam.http.requests.ServiceInit
import jdi.api.facilitator.registration.RegistrationApiService
import jdi.api.profilerator.user_info.UserInfoApiService
import jdi.dto.user.UserSteps
import org.testng.annotations.BeforeSuite

open class BaseTest {

    val userSteps: UserSteps = UserSteps()

    @BeforeSuite(alwaysRun = true)
    open fun initService() {
        ServiceInit.init(RegistrationApiService::class.java)
        ServiceInit.init(UserInfoApiService::class.java)
    }
}
