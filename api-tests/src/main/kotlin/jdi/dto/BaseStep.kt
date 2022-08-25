package jdi.dto

import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class BaseStep {

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
