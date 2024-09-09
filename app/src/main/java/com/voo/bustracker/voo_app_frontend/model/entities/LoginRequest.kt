package com.voo.bustracker.voo_app_frontend.model.entities


data class LoginRequest(
    val telefono_: String,
    val clave: String
)

data class LoginResponse(
    val token: String,
    val user: User
)

data class User(
    val id: String,
    val telefono_: String,
    val nombre: String,
    val apellido: String,
    val role: String)