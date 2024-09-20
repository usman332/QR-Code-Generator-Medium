package com.usman.qrcodegenratorscanner.presenter.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.usman.qrcodegenratorscanner.image.ImageSaveShare
import com.usman.qrcodegenratorscanner.image.getImageSaveShare
import com.usman.qrcodegenratorscanner.presenter.ui.components.oneCode
import com.usman.qrcodegenratorscanner.presenter.viewmodel.QRGenerateViewModel
import com.usman.qrcodegenratorscanner.util.TimeDateUtil
import io.github.alexzhirkevich.qrose.QrData
import io.github.alexzhirkevich.qrose.rememberQrCodePainter
import io.github.alexzhirkevich.qrose.text
import io.github.alexzhirkevich.qrose.toImageBitmap
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import qrcodegeneratorscanner.composeapp.generated.resources.Res
import qrcodegeneratorscanner.composeapp.generated.resources.*

/**
 * A composable function that generates a QR code based on the provided text and allows the user to share
 * or save the generated QR code to the device gallery. It also displays a message when the QR code is saved.
 *
 * @param text              The text input that will be encoded into a QR code.
 * @param qrGenerateViewModel The ViewModel that handles QR code sharing and saving functionalities.
 * @param modifier          An optional [Modifier] to be applied to the root layout for layout customization.
 *
 * Functionality:
 * - Displays a QR code generated from the provided `text`.
 * - Provides buttons to either share the QR code or save it to the gallery.
 * - When the "Share" button is clicked, the QR code is converted into a bitmap and shared via the [qrGenerateViewModel].
 * - When the "Save" button is clicked, the QR code is saved to the gallery using the [qrGenerateViewModel].
 * - If saving or sharing fails, a message is displayed to the user.
 * - A temporary toast message is shown when the QR code is successfully saved to the gallery.
 *
 */

@Composable
fun QRGenerateScreen(
    text: String,
    qrGenerateViewModel: QRGenerateViewModel,
    modifier: Modifier = Modifier
) {
    var showMessage by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    )
    {

        val painter = rememberQrCodePainter(QrData.text(text))
        oneCode("", painter, modifier)


        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            //region share
            Button(
                onClick = {

                    val imageBitmap = painter.toImageBitmap(1024, 1024)

                    qrGenerateViewModel.shareQrCode(
                        imageBitmap,
                        "qr_code" + TimeDateUtil.getCurrentDateTime() + ".png"
                    ) { shared ->
                        if (!shared) {
                            showMessage = true

                        }
                    }

                },
                modifier = Modifier
                    .padding(8.dp)
                    .height(48.dp)
                    .weight(1f) // Equal width based on row weight


            ) {
                Text(
                    text =
                    stringResource(Res.string.share),
                    fontSize = 20.sp
                )
            }
            //endregion

            //region save to gallery
            Button(
                onClick = {
                    //  saveQrCodeToGallery(context, qrCodeBitmap)
                    val imageBitmap = painter.toImageBitmap(1024, 1024)
                    qrGenerateViewModel.saveQrCodeToGallery(
                        imageBitmap,
                        "qr_code" + TimeDateUtil.getCurrentDateTime() + ".png"
                    ) { saved ->
                        if (saved) {
                            showMessage = true
                        }
                    }

                },
                modifier = Modifier
                    .padding(8.dp)
                    .height(48.dp)
                    .weight(1f)
                // Equal width based on row weight

            ) {
                Text(
                    text =
                    stringResource(Res.string.gallery),
                    fontSize = 20.sp
                )
            }
            //endregion

        }
        //State Hoasting

        if (showMessage) {
            showChipToast(stringResource(Res.string.save_to_gallery))
            LaunchedEffect(Unit) {
                delay(1000L)
                showMessage = false
            }
        }
    }
}

@Composable
private fun showChipToast(text: String) {
    AssistChip(
        shape = MaterialTheme.shapes.small,
        colors = AssistChipDefaults.assistChipColors(
            leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        onClick = { },
        label = {
            Text(text = text)
        },
        modifier = Modifier.padding(8.dp)
    )
}

