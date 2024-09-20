package com.usman.qrcodegenratorscanner.util

import kotlinx.datetime.Clock

object TimeDateUtil {

    fun getCurrentDateTime(): String {
        val currentDateTime = Clock.System.now()
        return currentDateTime.toString()
    }

}