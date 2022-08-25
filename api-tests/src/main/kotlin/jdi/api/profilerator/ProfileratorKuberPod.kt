package jdi.api.profilerator

import jdi.api.TangoKuberPod

open class ProfileratorKuberPod: TangoKuberPod() {

    companion object {
        const val PROFILERATOR_KUBER_POD_URI = "$KUBER_POD_URI/profilerator"
    }
}
