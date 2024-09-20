package com.usman.qrcodegenratorscanner.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

// Common code
actual fun ioDispatcher(): CoroutineDispatcher = Dispatchers.Default
