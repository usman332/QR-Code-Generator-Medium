package com.usman.qrcodegenratorscanner

import androidx.compose.runtime.Composable
import com.usman.qrcodegenratorscanner.presenter.ui.QRApp
import com.usman.qrcodegenratorscanner.presenter.ui.theme.QRTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    QRTheme {
        QRApp()
    }
}
