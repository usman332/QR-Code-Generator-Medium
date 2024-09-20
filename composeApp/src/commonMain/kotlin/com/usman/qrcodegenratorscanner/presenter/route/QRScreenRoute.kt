package com.usman.qrcodegenratorscanner.presenter.route

import org.jetbrains.compose.resources.StringResource
import qrcodegeneratorscanner.composeapp.generated.resources.Res
import qrcodegeneratorscanner.composeapp.generated.resources.app_name
import qrcodegeneratorscanner.composeapp.generated.resources.email
import qrcodegeneratorscanner.composeapp.generated.resources.*


enum class QRScreenRoute(val title: StringResource) {
    Start(title = Res.string.app_name),
    TEXT(title = Res.string.text),
    EMAIL(title = Res.string.email),
    QRCODE(title = Res.string.qr_code)

}

enum class  OptionName{
    TEXT, EMAIL,

    QRCODE
}