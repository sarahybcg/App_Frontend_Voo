package com.voo.bustracker.voo_app_frontend.model.entities

import java.util.*

data class UserInput(
    val name: String = "",
    val surname: String = "",
    val phone: String = "",
    val birthDate: Calendar? = null,
    val id: String = "",
    val password: String = "",
    val termsAccepted: Boolean = false
)