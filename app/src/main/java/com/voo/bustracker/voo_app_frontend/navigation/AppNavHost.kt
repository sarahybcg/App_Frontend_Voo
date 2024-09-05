package com.voo.bustracker.voo_app_frontend.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.voo.bustracker.voo_app_frontend.ui.view.login.LoginScreen
import com.voo.bustracker.voo_app_frontend.ui.view.onboarding.PassengerOnboardingScreen
import com.voo.bustracker.voo_app_frontend.ui.view.onboarding.DriverOnboardingScreen
import com.voo.bustracker.voo_app_frontend.ui.view.register.DriverRegistrationScreen
import com.voo.bustracker.voo_app_frontend.ui.view.register.PassengerRegistrationScreen
import com.voo.bustracker.voo_app_frontend.ui.view.selection.SelectionScreen
import androidx.navigation.compose.NavHost
import com.voo.bustracker.voo_app_frontend.ui.view.home.HomeDriver

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
    }
}