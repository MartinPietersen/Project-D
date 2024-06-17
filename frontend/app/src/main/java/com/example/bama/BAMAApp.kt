package com.example.bama

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class BamaScreens(@StringRes val title: Int) {
    Login(title = R.string.login),
    ActivitiesPage(title = R.string.activities_page),
    ForgotPasswordPage(title = R.string.forgot_password_page),
    ActivityDetails(title = R.string.activity_details),
    CreateNewAccount(title = R.string.create_new_account),
    CreateNewAccountTwo(title = R.string.create_new_account_two),
    HomePage(title = R.string.home_page),
    ChatPage(title = R.string.chat_page),
    ChatOverviewPage(title = R.string.chat_overview_page)
}

@Composable
fun BAMAApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = BamaScreens.Login.name
    ) {
        composable(route = BamaScreens.HomePage.name){
            HomePage(navController)
        }

        composable(route = BamaScreens.Login.name) {
            LoginPage(navController)
        }
        composable(route = BamaScreens.ActivitiesPage.name) {
            ActivitiesPage(navController)
        }
        composable(route = BamaScreens.ForgotPasswordPage.name) {
            ForgotPasswordPage(navController)
        }
        // dialog destinations are used
        composable(route = BamaScreens.ActivityDetails.name) {
            ActivityInformationScreen(navController)
        }
        composable(route = BamaScreens.CreateNewAccount.name) {
            CreateNewAccount(navController)
        }
        composable(route = BamaScreens.CreateNewAccountTwo.name) {
            CreateNewAccountTwo(navController)
        }
        composable(route = BamaScreens.ChatPage.name) {
            ChatPage(navController)
        }
        composable(route = BamaScreens.ChatOverviewPage.name) {
            ChatOverviewPage(navController)
        }
    }
}