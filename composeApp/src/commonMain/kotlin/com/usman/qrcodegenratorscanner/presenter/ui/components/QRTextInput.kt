package com.usman.qrcodegenratorscanner.presenter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import qrcodegeneratorscanner.composeapp.generated.resources.Res
import qrcodegeneratorscanner.composeapp.generated.resources.enter_text
import qrcodegeneratorscanner.composeapp.generated.resources.generate_qr_code
import qrcodegeneratorscanner.composeapp.generated.resources.ic_text
import qrcodegeneratorscanner.composeapp.generated.resources.text

/**
 * A composable function that allows users to input text and generate a QR code based on the provided input.
 * It includes input validation and displays an error message if the input is invalid.
 *
 * @param onGenerateClicked A callback function that is invoked when the user clicks the "Generate" button.
 *                          The function passes the input text to this callback if the input is valid.
 * @param modifier          An optional [Modifier] to be applied to the root layout of this composable for
 *                          customization (e.g., padding, alignment, etc.). It defaults to an empty [Modifier].
 *
 * Functionality:
 * - Displays an input field for users to enter text.
 * - The input is limited to a maximum of 150 characters.
 * - If the user attempts to generate a QR code with an empty input or input that is less than 5 characters,
 *   an error message is shown.
 * - When the "Done" button on the software keyboard is pressed, the keyboard is dismissed.
 * - Upon clicking the "Generate" button, the entered text is passed to the [onGenerateClicked] callback if
 *   the input is valid.
 *
 */
@Composable
fun QRTextInput(
    onGenerateClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var inputText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Get the software keyboard controller
    val keyboardController = LocalSoftwareKeyboardController.current


    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),

        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.background),

        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Icon(
                painter = painterResource(Res.drawable.ic_text), // Example icon, replace with your own
                contentDescription = stringResource(Res.string.text),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = inputText,
                onValueChange = {
                    if (it.length <= 150)
                        inputText = it
                },
                label = { Text(stringResource(Res.string.enter_text)) },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                maxLines = 3,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done, // Specifies the action button
                    keyboardType = KeyboardType.Text // Specifies the input type, e.g., text, number, etc.
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Handle the "Done" action here
                        // You might want to hide the keyboard or perform another action
                        keyboardController?.hide()
                    }
                )
            )

            errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (inputText.isBlank()) {
                        errorMessage = "Input cannot be empty"
                    } else if (inputText.length < 5) {
                        errorMessage = "Input must be at least 5 characters"
                    } else {
                        errorMessage = null // Clear the error
                        onGenerateClicked(inputText)
                    }


                },
                modifier = Modifier.align(Alignment.End).padding(16.dp)
            ) {
                Text(
                    text = stringResource(Res.string.generate_qr_code),
                    fontSize = 20.sp
                )
            }
        }

    }
}
