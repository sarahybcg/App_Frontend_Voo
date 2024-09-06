package com.voo.bustracker.voo_app_frontend.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.voo.bustracker.voo_app_frontend.ui.view.home.HomeDriver
import com.voo.bustracker.voo_app_frontend.ui.view.login.LoginScreen
import com.voo.bustracker.voo_app_frontend.ui.view.onboarding.DriverOnboardingScreen
import com.voo.bustracker.voo_app_frontend.ui.view.onboarding.PassengerOnboardingScreen
import com.voo.bustracker.voo_app_frontend.ui.view.profile.driver.SendFriendRequest
import com.voo.bustracker.voo_app_frontend.ui.view.register.DriverRegistrationScreen
import com.voo.bustracker.voo_app_frontend.ui.view.register.PassengerRegistrationScreen
import com.voo.bustracker.voo_app_frontend.ui.view.selection.SelectionScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Selection.route) {
            SelectionScreen(navController)
        }
        composable(Screen.PassengerOnboarding.route) {
            PassengerOnboardingScreen(navController)
        }
        composable(Screen.DriverOnboarding.route) {
            DriverOnboardingScreen(navController)
        }
        composable(Screen.DriverRegister.route) {
            DriverRegistrationScreen(navController)
        }
        composable(Screen.PassengerRegister.route) {
            PassengerRegistrationScreen(navController)
        }
        composable(Screen.HomeDriver.route) {
            HomeDriver()
        }
        composable(Screen.SendFriendRequest.route) {
            SendFriendRequest(
                onSendRequest = { username ->
                    println("Solicitud de amistad enviada a $username")
                },
                navController = navController
            )
        }
    }
}