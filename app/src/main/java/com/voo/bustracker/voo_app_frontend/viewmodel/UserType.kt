package com.voo.bustracker.voo_app_frontend.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.voo.bustracker.voo_app_frontend.model.entities.UserInput

enum class UserType {
    PASSENGER, DRIVER
}

class LoginViewModel : ViewModel() {
    var userInput = mutableStateOf(UserInput())
        private set

    var selectedUserType = mutableStateOf(UserType.PASSENGER)
        private set

    fun updatePhone(newPhone: String) {
        userInput.value = userInput.value.copy(phone = newPhone)
    }

    fun updatePassword(newPassword: String) {
        userInput.value = userInput.value.copy(password = newPassword)
    }

    fun setUserType(userType: UserType) {
        selectedUserType.value = userType
    }

    fun onLogin() {
        // Lógica de inicio de sesión
    }
}