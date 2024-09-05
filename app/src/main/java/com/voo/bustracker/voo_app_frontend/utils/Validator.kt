package com.voo.bustracker.voo_app_frontend.utils

object Validator {

    // Validación de campos vacíos
    fun isNotEmpty(value: String): Boolean {
        return value.isNotBlank()
    }

    // Validación de cédula venezolana o extranjera
    fun isCedulaValid(cedula: String, isVenezuelan: Boolean): Boolean {
        val venezuelanPattern = Regex("^[VvEe]-\\d{5,8}\$")
        val foreignPattern = Regex("^\\d{6,12}\$")
        return if (isVenezuelan) {
            venezuelanPattern.matches(cedula)
        } else {
            foreignPattern.matches(cedula)
        }
    }

    // Validación de número de teléfono (básica)
    fun isPhoneValid(phone: String): Boolean {
        val phonePattern = Regex("^\\+?\\d{10,15}\$")
        return phonePattern.matches(phone)
    }

    // Validación de nombre o apellido (sin caracteres extraños)
    fun isNameValid(name: String): Boolean {
        val namePattern = Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+\$")
        return namePattern.matches(name)
    }

    // Validación de contraseña segura
    fun isPasswordSecure(password: String): Boolean {
        val passwordPattern = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*]).{8,}\$")
        return passwordPattern.matches(password)
    }

    // Coincidencia de contraseñas
    fun arePasswordsMatching(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

}