package com.usman.qrcodegenratorscanner.data.model

import org.jetbrains.compose.resources.DrawableResource

data class QROption(
    val route :String,
    val text: String, val icon:DrawableResource)

