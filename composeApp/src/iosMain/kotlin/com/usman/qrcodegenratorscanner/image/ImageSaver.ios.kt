package com.usman.qrcodegenratorscanner.image

actual fun getImageSaveShare(): ImageSaveShare {
    return IOSImageSaveShareImpl()
}