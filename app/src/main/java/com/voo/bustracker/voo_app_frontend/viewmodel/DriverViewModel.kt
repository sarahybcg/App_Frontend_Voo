package com.voo.bustracker.voo_app_frontend.viewmodel

import androidx.lifecycle.ViewModel
import com.voo.bustracker.voo_app_frontend.model.entities.UserInput
import androidx.compose.runtime.mutableStateOf
import com.voo.bustracker.voo_app_frontend.components.register.FieldError
import com.voo.bustracker.voo_app_frontend.model.entities.CountryCode
import com.voo.bustracker.voo_app_frontend.utils.CountryCodeData
import com.voo.bustracker.voo_app_frontend.utils.Validator

class DriverViewModel : ViewModel() {
    var userInput = mutableStateOf(UserInput())
        private set


    var drivingLicense = mutableStateOf<String?>(null)
        private set

    var fieldErrors = mutableStateOf(FieldError())
        private set


    fun updateUserInput(newInput: UserInput) {
        userInput.value = newInput
    }

    fun updateDrivingLicense(licensePath: String) {
        drivingLicense.value = licensePath
    }
    var selectedCountryCode = mutableStateOf<CountryCode?>(null)

    val countryCodes = CountryCodeData.countryCodes

    fun selectCountryCode(countryCode: CountryCode) {
        selectedCountryCode.value = countryCode
    }
    fun validateInput(): Boolean {
        val user = userInput.value
        var hasError = false

        val newFieldErrors = FieldError(
            nameError = when {
                !Validator.isNotEmpty(user.name) -> "El nombre no puede estar vacío"
                !Validator.isNameValid(user.name) -> "El nombre contiene caracteres inválidos"
                else -> null
            },
            surnameError = when {
                !Validator.isNotEmpty(user.surname) -> "El apellido no puede estar vacío"
                !Validator.isNameValid(user.surname) -> "El apellido contiene caracteres inválidos"
                else -> null
            },
            phoneError = when {
                !Validator.isPhoneValid(user.phone) -> "El número de teléfono no es válido"
                else -> null
            },
            idError = when {
                !Validator.isCedulaValid(user.id, isVenezuelan = true) -> "La cédula no es válida"
                else -> null
            },
            passwordError = when {
                !Validator.isPasswordSecure(user.password) -> "La contraseña no es segura"
                else -> null
            },
            termsAcceptedError = if (!user.termsAccepted) "Debe aceptar los términos y condiciones" else null,
            drivingLicenseError = if (drivingLicense.value.isNullOrEmpty()) "Debe adjuntar la licencia de conducir" else null
        )

        // Actualiza el estado de los errores
        fieldErrors.value = newFieldErrors

        // Comprueba si hay algún error
        hasError = newFieldErrors.nameError != null ||
                newFieldErrors.surnameError != null ||
                newFieldErrors.phoneError != null ||
                newFieldErrors.idError != null ||
                newFieldErrors.passwordError != null ||
                newFieldErrors.confirmPasswordError != null ||
                newFieldErrors.termsAcceptedError != null ||
                newFieldErrors.drivingLicenseError != null

        return !hasError
    }

    fun onDriver() {
        if (validateInput()) {
            // Lógica de registro
        }
    }
}