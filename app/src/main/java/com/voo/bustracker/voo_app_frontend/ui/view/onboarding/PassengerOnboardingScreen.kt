package com.voo.bustracker.voo_app_frontend.ui.view.onboarding

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.voo.bustracker.voo_app_frontend.navigation.Screen


@Composable
fun PassengerOnboardingScreen(navController: NavController) {
    Log.d("DriverRegisterScreen", "Pantalla de registro de conductor cargada")
    OnboardingScreen(
        navController = navController,
        destination = Screen.PassengerRegister.route,
        isPassenger = true
    )
}
