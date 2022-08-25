package jdi.api.facilitator

import jdi.api.TangoKuberPod

open class FacilitatorKuberPod: TangoKuberPod() {

    companion object {
        const val FACILITATOR_KUBER_POD_URI = "$KUBER_POD_URI/facilitator"
    }
}
