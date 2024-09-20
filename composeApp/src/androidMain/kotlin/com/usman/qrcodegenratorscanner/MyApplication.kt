package com.usman.qrcodegenratorscanner

import android.app.Application
import com.usman.qrcodegenratorscanner.util.ContextProvider

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ContextProvider.initialize(applicationContext)
    }
}