package com.usman.qrcodegenratorscanner.presenter.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.usman.qrcodegenratorscanner.data.source.getQROptions
import com.usman.qrcodegenratorscanner.presenter.route.QRScreenRoute
import com.usman.qrcodegenratorscanner.presenter.ui.components.QRIconTextBox

@Composable
fun QRDashBoardScreen(
    onOptionClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val itemList = getQROptions()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center // Center the grid content within the box
    ){
        LazyVerticalGrid(
            columns = GridCells.Adaptive(96.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = modifier.wrapContentHeight()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.spacedBy(16.dp), // Equal horizontal spacing between items
            verticalArrangement = Arrangement.spacedBy(16.dp), // Equal vertical spacing between items
        ) {
            items(itemList) { option ->
                QRIconTextBox(option.text, option.icon, onClick = {
                    onOptionClicked(option.route)
                })
            }

        }
    }


}