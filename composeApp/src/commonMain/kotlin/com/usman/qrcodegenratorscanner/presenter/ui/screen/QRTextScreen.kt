package com.usman.qrcodegenratorscanner.presenter.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.usman.qrcodegenratorscanner.presenter.ui.components.QRTextInput

@Composable
fun QRTextScreen(
    onGenerateClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    QRTextInput(onGenerateClicked, modifier)
}