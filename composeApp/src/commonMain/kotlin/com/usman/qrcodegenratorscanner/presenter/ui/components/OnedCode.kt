package com.usman.qrcodegenratorscanner.presenter.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp


@Composable
fun oneCode(
    name: String,
    code: Painter,
    modifier: Modifier
) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(24.dp)
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.background )
            .heightIn(max = 250.dp) // Set the max height to 250dp

    ) {
        Image(
            painter = code,
            contentDescription = null,
            modifier = Modifier
                .width(320.dp)
                .height(320.dp)
        )


        Text(name)
    }
}

