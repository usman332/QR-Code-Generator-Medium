package com.usman.qrcodegenratorscanner.data.source

import androidx.compose.runtime.Composable
import com.usman.qrcodegenratorscanner.data.model.QROption
import com.usman.qrcodegenratorscanner.presenter.route.OptionName
import org.jetbrains.compose.resources.stringResource
import qrcodegeneratorscanner.composeapp.generated.resources.Res
import qrcodegeneratorscanner.composeapp.generated.resources.*

@Composable
fun getQROptions(): List<QROption> {
    return listOf(
        QROption(
            route = OptionName.TEXT.name,
            text = stringResource(Res.string.text),
            icon = Res.drawable.ic_text
        ),


        QROption(
            route = OptionName.TEXT.name,
            text = stringResource(Res.string.text),
            icon = Res.drawable.ic_text
        ),
        QROption(
            route = OptionName.EMAIL.name,
            text = stringResource(Res.string.email),
            icon = Res.drawable.ic_text
        ),




    )
}