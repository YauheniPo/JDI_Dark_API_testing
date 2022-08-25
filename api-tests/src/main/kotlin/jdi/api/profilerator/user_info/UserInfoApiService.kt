package jdi.api.profilerator.user_info

import com.epam.http.annotations.*
import com.epam.http.requests.RestMethod
import com.google.common.net.HttpHeaders
import jdi.api.profilerator.ProfileratorKuberPod


@ServiceDomain(ProfileratorKuberPod.PROFILERATOR_KUBER_POD_URI)
class UserInfoApiService: ProfileratorKuberPod() {

    companion object {
        private const val USER_INFO_REST = "profiles.proto"

        @Headers(
            Header(name = HttpHeaders.ACCEPT, value = "application/x-protobuf"),
            Header(name = HttpHeaders.CONTENT_TYPE, value = "application/x-protobuf")
        )
        @POST(USER_INFO_REST)
        lateinit var userInfo: RestMethod
    }
}
