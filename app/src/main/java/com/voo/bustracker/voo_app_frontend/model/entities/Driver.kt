package com.voo.bustracker.voo_app_frontend.model.entities


data class Driver(
    val user: UserInput,
    val drivingLicense: String? // Path o URL de la licencia de conducir adjunta
)