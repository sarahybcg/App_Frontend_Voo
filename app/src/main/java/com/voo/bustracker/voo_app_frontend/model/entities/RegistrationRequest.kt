package com.voo.bustracker.voo_app_frontend.model.entities

    data class RegistrationRequest(
        val datosPrincipales: DatosPrincipales,
        val datosAdicionales: DatosAdicionales
    )

    data class DatosPrincipales(
        val CI_: String,
        val nombre: String,
        val apellido: String,
        val telefono_: String,
        val fechaNacimiento: String,
        val clave: String,
        val role: String
    )

    data class DatosAdicionales(
        val licenciaConducir: String?
    )
