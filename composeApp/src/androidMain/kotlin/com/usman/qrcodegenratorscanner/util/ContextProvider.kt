package com.usman.qrcodegenratorscanner.util

import android.content.Context

// In your Android-specific module
object ContextProvider {
    lateinit var context: Context
        private set

    fun initialize(context: Context) {
        this.context = context
    }
}
