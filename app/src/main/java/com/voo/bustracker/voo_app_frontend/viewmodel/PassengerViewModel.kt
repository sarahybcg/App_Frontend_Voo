package com.voo.bustracker.voo_app_frontend.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.voo.bustracker.voo_app_frontend.model.entities.CountryCode
import com.voo.bustracker.voo_app_frontend.model.entities.UserInput
import com.voo.bustracker.voo_app_frontend.utils.CountryCodeData
import com.voo.bustracker.voo_app_frontend.utils.Validator


class PassengerViewModel : ViewModel() {
    var userInput = mutableStateOf(UserInput())
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun updateUserInput(newInput: UserInput) {
        userInput.value = newInput
    }

    var selectedCountryCode = mutableStateOf<CountryCode?>(null)

    val countryCodes = CountryCodeData.countryCodes

    fun selectCountryCode(countryCode: CountryCode) {
        selectedCountryCode.value = countryCode
    }

    fun validateInput(): Boolean {
        val user = userInput.value

        if (!Validator.isNotEmpty(user.name)) {
            errorMessage.value = "El nombre no puede estar vacío"
            return false
        }
        if (!Validator.isNotEmpty(user.surname)) {
            errorMessage.value = "El apellido no puede estar vacío"
            return false
        }
        if (!Validator.isNameValid(user.name) || !Validator.isNameValid(user.surname)) {
            errorMessage.value = "El nombre o apellido contiene caracteres inválidos"
            return false
        }
        if (!Validator.isPhoneValid(user.phone)) {
            errorMessage.value = "El número de teléfono no es válido"
            return false
        }
        if (!Validator.isCedulaValid(user.id, isVenezuelan = true)) {
            errorMessage.value = "La cédula no es válida"
            return false
        }
        if (!Validator.isPasswordSecure(user.password)) {
            errorMessage.value = "La contraseña no es segura"
            return false
        }
        if (!user.termsAccepted) {
            errorMessage.value = "Debe aceptar los términos y condiciones"
            return false
        }

        errorMessage.value = null // No hay errores
        return true
    }

    fun onPassenger() {
        if (validateInput()) {
            // Lógica de registro
        }
    }
}