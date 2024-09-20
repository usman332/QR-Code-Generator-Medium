package com.usman.qrcodegenratorscanner.presenter.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
/**
 * QRIconTextBox is a reusable UI component that displays an icon and a text label inside a clickable card.
 *
 * @param text The text to be displayed below the icon.
 * @param icon The drawable resource ID for the icon to be displayed above the text.
 * @param onClick A lambda function to handle the click event for the card.
 *
 * This composable consists of a `Card` that contains a `Column`, with an `Icon` and a `Text` arranged vertically.
 * The card is clickable and performs the action defined by the `onClick` function.
 *
 * Example Usage:
 *
 * ```
 * QRIconTextBox(
 *    text = "Scan QR Code",
 *    icon = R.drawable.ic_qr_code,
 *    onClick = { /* Navigate to next screen */ }
 * )
 * ```
 */
@Composable
fun QRIconTextBox(
    text: String, icon: DrawableResource,
    onClick: () -> Unit // Add an onClick parameter to handle clicks
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier
            .size(width = 96.dp, height = 100.dp)
            .padding(8.dp)
            .clickable(onClick = onClick) // Make the card clickable

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center, // Center content vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally

        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }

}


