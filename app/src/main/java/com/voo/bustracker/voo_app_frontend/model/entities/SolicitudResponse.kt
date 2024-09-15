package com.voo.bustracker.voo_app_frontend.model.entities


data class UserData(
    val nombre: String,
    val apellido: String,
    val telefono: String
)

data class UserResponse(
    val message: String,
    val data: UserData?
)

data class MessageResponse(
    val message: String
)

data class SolicitudResponse(
    val id: Int,
    val estado: String,
    val created_at: String,
    val updated_at: String,
    val receptor: UserData? = null,
    val solicitante: UserData? = null
)
