package com.voo.bustracker.voo_app_frontend.ui.view.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.voo.bustracker.voo_app_frontend.navigation.Screen

@Composable
fun PassengerOnboardingScreen(navController: NavController) {
    OnboardingScreen(navController = navController, destination = Screen.PassengerRegister.route, isPassenger = true)
}