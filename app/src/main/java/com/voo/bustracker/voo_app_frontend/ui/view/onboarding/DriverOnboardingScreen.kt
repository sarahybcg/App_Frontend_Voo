package com.voo.bustracker.voo_app_frontend.ui.view.onboarding


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.voo.bustracker.voo_app_frontend.navigation.Screen

@Composable
fun DriverOnboardingScreen(navController: NavController) {
    Log.d("PassengerRegisterScreen", "Pantalla de registro de pasajero cargada")
    OnboardingScreen(
        navController = navController,
        destination = Screen.DriverRegister.route,
        isPassenger = false
    )
}
