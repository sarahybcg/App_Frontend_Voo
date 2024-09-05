package com.voo.bustracker.voo_app_frontend.components.register

data class FieldError(
    val nameError: String? = null,
    val surnameError: String? = null,
    val phoneError: String? = null,
    val idError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val termsAcceptedError: String? = null,
    val drivingLicenseError: String? = null
)