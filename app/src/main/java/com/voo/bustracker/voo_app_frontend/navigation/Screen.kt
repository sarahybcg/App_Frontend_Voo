package com.voo.bustracker.voo_app_frontend.navigation

sealed class Screen(val route: String) {
    data object Selection : Screen("selection")
    data object PassengerOnboarding : Screen("passenger_onboarding")
    data object DriverOnboarding : Screen("driver_onboarding")
    data object Login : Screen("login")
    data object DriverRegister : Screen("driver_register")
    data object PassengerRegister : Screen("passenger_register")
    data object HomeDriver : Screen("home_driver")
}
