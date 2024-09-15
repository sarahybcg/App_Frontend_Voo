package com.voo.bustracker.voo_app_frontend.network

import com.voo.bustracker.voo_app_frontend.model.entities.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("app/login")
    suspend fun login(@Body userInput: DatosPrincipales): Response<LoginResponse>

    @POST("store")
    suspend fun registerUser(@Body request: RegisterRequest): Response<RegisterResponse>

    // Buscar usuario por teléfono
    @GET("buscar-usuario-por-telefono")
    fun buscarUsuarioPorTelefono(@Query("telefono") telefono: String): Call<UserResponse>

    // Enviar solicitud
    @POST("enviar-solicitud")
    fun enviarSolicitud(@Body request: EnviarSolicitudRequest): Call<MessageResponse>

    // Responder solicitud
    @POST("responder-solicitud/{id}")
    fun responderSolicitud(
        @Path("id") id: Int,
        @Body request: ResponderSolicitudRequest
    ): Call<MessageResponse>

    // Ver solicitudes enviadas
    @GET("solicitudes-enviadas")
    fun solicitudesEnviadas(@Query("solicitante_id") solicitanteId: Int): Call<List<SolicitudResponse>>

    // Ver solicitudes recibidas
    @GET("solicitudes-recibidas")
    fun solicitudesRecibidas(@Query("receptor_id") receptorId: Int): Call<List<SolicitudResponse>>
}