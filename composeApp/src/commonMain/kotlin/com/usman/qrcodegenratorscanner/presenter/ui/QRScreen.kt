package com.usman.qrcodegenratorscanner.presenter.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.usman.qrcodegenratorscanner.presenter.route.QRScreenRoute
import com.usman.qrcodegenratorscanner.presenter.ui.screen.QRDashBoardScreen
import com.usman.qrcodegenratorscanner.presenter.ui.screen.QRGenerateScreen
import com.usman.qrcodegenratorscanner.presenter.ui.screen.QRTextScreen
import com.usman.qrcodegenratorscanner.presenter.viewmodel.QRGenerateViewModel
import com.usman.qrcodegenratorscanner.presenter.viewmodel.SharedViewModel
import org.jetbrains.compose.resources.stringResource
import qrcodegeneratorscanner.composeapp.generated.resources.Res
import qrcodegeneratorscanner.composeapp.generated.resources.back_button


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QRAppBar(
    currentScreen: QRScreenRoute,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(

        title = {
            Text(
                text = stringResource(currentScreen.title),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {

                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(Res.string.back_button)
                    )
                }

            }

        }

    )
}

// Screen Navigation
@Composable
fun QRApp(
    sharedViewModel: SharedViewModel = viewModel { SharedViewModel() },
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = QRScreenRoute.valueOf(
        backStackEntry?.destination?.route ?: QRScreenRoute.Start.name
    )
    Scaffold(
        topBar = {
            QRAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = QRScreenRoute.Start.name,
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = QRScreenRoute.Start.name) {
                QRDashBoardScreen(
                    onOptionClicked = { route ->
                        when (route) {
                            QRScreenRoute.TEXT.name -> {
                                navController.navigate(QRScreenRoute.TEXT.name)
                            }
                        }
                    }, modifier = Modifier.fillMaxHeight()
                )
            }

            composable(route = QRScreenRoute.TEXT.name) {
                QRTextScreen(
                    onGenerateClicked = {

                        sharedViewModel.setTextForQR(it)
                        navController.navigate(QRScreenRoute.QRCODE.name)
                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }

            composable(route = QRScreenRoute.QRCODE.name) {

                 val qrGenerateViewModel: QRGenerateViewModel = viewModel { QRGenerateViewModel() }

                val data = sharedViewModel.getTextForQR()
                QRGenerateScreen(data,qrGenerateViewModel, modifier = Modifier.fillMaxHeight())
            }
        }

    }


}
