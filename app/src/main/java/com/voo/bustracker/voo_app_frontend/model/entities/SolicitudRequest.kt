package com.voo.bustracker.voo_app_frontend.model.entities

data class EnviarSolicitudRequest(
    val telefono: String,
    val solicitante_id: Int
)

data class ResponderSolicitudRequest(
    val estado: String,
    val receptor_id: Int
)