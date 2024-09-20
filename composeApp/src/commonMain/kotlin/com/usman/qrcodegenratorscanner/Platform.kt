package com.usman.qrcodegenratorscanner

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform