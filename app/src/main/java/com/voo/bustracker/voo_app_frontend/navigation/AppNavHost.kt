package com.voo.bustracker.voo_app_frontend.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.voo.bustracker.voo_app_frontend.model.repository.UserRepository
import com.voo.bustracker.voo_app_frontend.ui.view.home.HomeDriver
import com.voo.bustracker.voo_app_frontend.ui.view.login.LoginScreen
import com.voo.bustracker.voo_app_frontend.ui.view.onboarding.DriverOnboardingScreen
import com.voo.bustracker.voo_app_frontend.ui.view.onboarding.PassengerOnboardingScreen
import com.voo.bustracker.voo_app_frontend.ui.view.profile.driver.BuscarUsuarioScreen
import com.voo.bustracker.voo_app_frontend.ui.view.register.DriverRegistrationScreen
//import com.voo.bustracker.voo_app_frontend.ui.view.register.PassengerRegistrationScreen
import com.voo.bustracker.voo_app_frontend.ui.view.selection.SelectionScreen

@Composable
@RequiresApi(Build.VERSION_CODES.Q) // Esto asegura que la función solo se use en API 29 o superior
fun AppNavGraph(navController: NavHostController, isLoggedIn: Boolean, userRepository: UserRepository) {
    val startDestination = if (isLoggedIn) {
        Screen.HomeDriver.route
    } else {
        Screen.Login.route
    }

    NavHost(navController = navController, startDestination = startDestination) {
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
            DriverRegistrationScreen(navController, userRepository)
        }

        composable(Screen.PassengerRegister.route) {
//            PassengerRegistrationScreen(navController, userRepository)
        }
        composable(Screen.HomeDriver.route) {
            HomeDriver()
        }

        composable(Screen.BuscarUsuario.route) {
            BuscarUsuarioScreen()
        }
    }
}