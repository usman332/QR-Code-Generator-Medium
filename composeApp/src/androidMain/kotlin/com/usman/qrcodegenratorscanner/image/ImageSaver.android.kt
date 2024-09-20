package com.usman.qrcodegenratorscanner.image

import com.usman.qrcodegenratorscanner.util.ContextProvider

actual fun getImageSaveShare(): ImageSaveShare {

    return AndroidImageSaveShareImpl(context = ContextProvider.context)
}