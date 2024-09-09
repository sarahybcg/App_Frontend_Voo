package com.voo.bustracker.voo_app_frontend.network

import android.content.Context
import com.voo.bustracker.voo_app_frontend.utils.SessionManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.0.105:8000/api/"
    private var sessionManager: SessionManager? = null

    fun initialize(context: Context) {
        sessionManager = SessionManager(context)
    }

    fun create(): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val token = sessionManager?.getAuthToken()  // Obtener el token desde SessionManager
                val newRequest = request.newBuilder()
                    .apply {
                        if (token != null) {
                            header("Authorization", "Bearer $token")
                        }
                    }
                    .build()
                chain.proceed(newRequest)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}
