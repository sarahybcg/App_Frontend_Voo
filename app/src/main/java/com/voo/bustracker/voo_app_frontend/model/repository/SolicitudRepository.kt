package com.voo.bustracker.voo_app_frontend.model.repository

import com.voo.bustracker.voo_app_frontend.model.entities.*
import com.voo.bustracker.voo_app_frontend.network.ApiService
import retrofit2.Response

// UsuarioRepository.kt
interface UsuarioRepository {
    suspend fun buscarUsuarioPorTelefono(telefono: String): UserResponse
    suspend fun enviarSolicitud(telefono: String, solicitanteId: Int): MessageResponse
    suspend fun responderSolicitud(id: Int, estado: String, receptorId: Int): MessageResponse
    suspend fun solicitudesEnviadas(solicitanteId: Int): List<SolicitudResponse>
    suspend fun solicitudesRecibidas(receptorId: Int): List<SolicitudResponse>
}

// UsuarioRepositoryImpl.kt
class UsuarioRepositoryImpl(private val apiService: ApiService) : UsuarioRepository {
    override suspend fun buscarUsuarioPorTelefono(telefono: String): UserResponse {
        return apiService.buscarUsuarioPorTelefono(telefono).execute().body()!!
    }

    override suspend fun enviarSolicitud(telefono: String, solicitanteId: Int): MessageResponse {
        return apiService.enviarSolicitud(EnviarSolicitudRequest(telefono, solicitanteId)).execute().body()!!
    }

    override suspend fun responderSolicitud(id: Int, estado: String, receptorId: Int): MessageResponse {
        return apiService.responderSolicitud(id, ResponderSolicitudRequest(estado, receptorId)).execute().body()!!
    }

    override suspend fun solicitudesEnviadas(solicitanteId: Int): List<SolicitudResponse> {
        return apiService.solicitudesEnviadas(solicitanteId).execute().body()!!
    }

    override suspend fun solicitudesRecibidas(receptorId: Int): List<SolicitudResponse> {
        return apiService.solicitudesRecibidas(receptorId).execute().body()!!
    }
}
