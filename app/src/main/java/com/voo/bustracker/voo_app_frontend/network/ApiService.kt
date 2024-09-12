package com.voo.bustracker.voo_app_frontend.network

import com.voo.bustracker.voo_app_frontend.model.entities.DatosPrincipales
import com.voo.bustracker.voo_app_frontend.model.entities.LoginResponse
import com.voo.bustracker.voo_app_frontend.model.entities.RegisterRequest
import com.voo.bustracker.voo_app_frontend.model.entities.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("app/login")
    suspend fun login(@Body userInput: DatosPrincipales): Response<LoginResponse>

    @POST("store")
    suspend fun registerUser(@Body request: RegisterRequest): Response<RegisterResponse>

}
