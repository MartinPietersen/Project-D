package com.example.bama

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class BamaScreens(@StringRes val title: Int) {
    Login(title = R.string.login),
    ActivitiesPage(title = R.string.activities_page),
    ForgotPasswordPage(title = R.string.forgot_password_page),
    ActivityDetails(title = R.string.activity_details)
}

//@Composable
//fun CupcakeApp(
//    viewModel: OrderViewModel = viewModel(),
//    navController: NavHostController = rememberNavController()
//) {
//    // Get current back stack entry
//    val backStackEntry by navController.currentBackStackEntryAsState()
//    // Get the name of the current screen
//    val currentScreen = CupcakeScreen.valueOf(
//        backStackEntry?.destination?.route ?: CupcakeScreen.Start.name
//    )
//
//    Scaffold(
//        topBar = {
//            CupcakeAppBar(
//                currentScreen = currentScreen,
//                canNavigateBack = navController.previousBackStackEntry != null,
//                navigateUp = { navController.navigateUp() }
//            )
//        }
//    ) { innerPadding ->
//        val uiState by viewModel.uiState.collectAsState()
//
//        NavHost(
//            navController = navController,
//            startDestination = CupcakeScreen.Start.name,
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//                .padding(innerPadding)
//        ) {
//            composable(route = CupcakeScreen.Start.name) {
//                StartOrderScreen(
//                    quantityOptions = DataSource.quantityOptions,
//                    onNextButtonClicked = {
//                        viewModel.setQuantity(it)
//                        navController.navigate(CupcakeScreen.Flavor.name)
//                    },
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(dimensionResource(R.dimen.padding_medium))
//                )
//            }
//            composable(route = CupcakeScreen.Flavor.name) {
//                val context = LocalContext.current
//                SelectOptionScreen(
//                    subtotal = uiState.price,
//                    onNextButtonClicked = { navController.navigate(CupcakeScreen.Pickup.name) },
//                    onCancelButtonClicked = {
//                        cancelOrderAndNavigateToStart(viewModel, navController)
//                    },
//                    options = DataSource.flavors.map { id -> context.resources.getString(id) },
//                    onSelectionChanged = { viewModel.setFlavor(it) },
//                    modifier = Modifier.fillMaxHeight()
//                )
//            }
//            composable(route = CupcakeScreen.Pickup.name) {
//                SelectOptionScreen(
//                    subtotal = uiState.price,
//                    onNextButtonClicked = { navController.navigate(CupcakeScreen.Summary.name) },
//                    onCancelButtonClicked = {
//                        cancelOrderAndNavigateToStart(viewModel, navController)
//                    },
//                    options = uiState.pickupOptions,
//                    onSelectionChanged = { viewModel.setDate(it) },
//                    modifier = Modifier.fillMaxHeight()
//                )
//            }
//            composable(route = CupcakeScreen.Summary.name) {
//                val context = LocalContext.current
//                OrderSummaryScreen(
//                    orderUiState = uiState,
//                    onCancelButtonClicked = {
//                        cancelOrderAndNavigateToStart(viewModel, navController)
//                    },
//                    onSendButtonClicked = { subject: String, summary: String ->
//                        shareOrder(context, subject = subject, summary = summary)
//                    },
//                    modifier = Modifier.fillMaxHeight()
//                )
//            }
//        }
//    }
//}
@Composable
fun BAMAApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = BamaScreens.Login.name
    ) {
        composable(route = BamaScreens.Login.name) {
            LoginPage(navController)
        }
        composable(route = BamaScreens.ActivitiesPage.name) {
            ActivitiesPage()
        }
        composable(route = BamaScreens.ForgotPasswordPage.name) {
            ForgotPasswordPage()
        }
    }
}

