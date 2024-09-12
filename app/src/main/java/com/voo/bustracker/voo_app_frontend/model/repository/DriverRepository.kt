package com.voo.bustracker.voo_app_frontend.model.repository

import com.voo.bustracker.voo_app_frontend.model.entities.RegisterRequest
import com.voo.bustracker.voo_app_frontend.model.entities.RegisterResponse
import com.voo.bustracker.voo_app_frontend.network.ApiService
import retrofit2.Response

interface UserRepository {
    suspend fun registerUser(request: RegisterRequest): Response<RegisterResponse>
}

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {
    override suspend fun registerUser(request: RegisterRequest): Response<RegisterResponse> {
        return apiService.registerUser(request)
    }
}



