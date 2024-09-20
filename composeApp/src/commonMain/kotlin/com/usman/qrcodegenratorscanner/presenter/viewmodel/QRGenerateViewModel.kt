package com.usman.qrcodegenratorscanner.presenter.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import com.usman.qrcodegenratorscanner.image.ImageSaveShare
import com.usman.qrcodegenratorscanner.image.getImageSaveShare
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for managing the generation and sharing of QR codes.
 *
 * This class provides methods to share QR code images and save them to the device's gallery.
 * It utilizes an instance of `ImageSaveShare` to handle image saving and sharing functionalities.
 */
class QRGenerateViewModel : ViewModel() {
    private val imageSaver: ImageSaveShare = getImageSaveShare() // Instance for saving and sharing images
    private val viewModelScope = CoroutineScope(Dispatchers.Main) // Coroutine scope for managing coroutines

    /**
     * Shares a QR code image using the configured image saver.
     *
     * @param imageBitmap The [ImageBitmap] representing the QR code to be shared.
     * @param fileName The name of the file under which the image will be saved before sharing.
     * @param onResult A callback function that will be invoked with the result of the share operation.
     *                  Receives `true` if the share was successful, `false` otherwise.
     *
     * This method launches a coroutine to perform the sharing operation on the main thread
     * and calls the provided callback with the result of the operation.
     */
    fun shareQrCode(imageBitmap: ImageBitmap, fileName: String, onResult: (Boolean) -> Unit) {

        viewModelScope.launch {
            val isShare = imageSaver.shareImage(imageBitmap, fileName)
            onResult(isShare)

        }
    }

    /**
     * Saves a QR code image to the device's gallery.
     *
     * @param imageBitmap The [ImageBitmap] representing the QR code to be saved.
     * @param fileName The name of the file under which the image will be saved.
     * @param onResult A callback function that will be invoked with the result of the save operation.
     *                  Receives `true` if the save was successful, `false` otherwise.
     *
     * This method launches a coroutine to perform the save operation on the main thread
     * and calls the provided callback with the result of the operation.
     */
    fun saveQrCodeToGallery(imageBitmap: ImageBitmap, fileName: String, onResult: (Boolean) -> Unit) {

        viewModelScope.launch {
            onResult(imageSaver.saveImage(imageBitmap, fileName))

        }
    }


}