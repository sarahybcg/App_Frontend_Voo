package com.voo.bustracker.voo_app_frontend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voo.bustracker.voo_app_frontend.network.ApiService
import com.voo.bustracker.voo_app_frontend.model.entities.*
import com.voo.bustracker.voo_app_frontend.network.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SolicitudViewModel : ViewModel() {
    private val apiService: ApiService = RetrofitClient.create()

    private val _userResponse = MutableLiveData<UserResponse?>()
    val userResponse: LiveData<UserResponse?> get() = _userResponse

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    // Nueva variable para manejar el estado de carga
    private val _isSearching = MutableLiveData<Boolean>()
    val isSearching: LiveData<Boolean> get() = _isSearching

    fun buscarUsuarioPorTelefono(telefono: String) {
        // Iniciar la búsqueda, activar el indicador de carga
        _isSearching.postValue(true)

        apiService.buscarUsuarioPorTelefono(telefono).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                // Desactivar el indicador de carga
                _isSearching.postValue(false)

                if (response.isSuccessful) {
                    _userResponse.postValue(response.body())
                } else {
                    _error.postValue("Usuario no encontrado")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isSearching.postValue(false)
                _error.postValue("Error al buscar usuario: ${t.message}")
            }
        })
    }


    fun enviarSolicitud(telefono: String, solicitanteId: Int) {
        val request = EnviarSolicitudRequest(telefono, solicitanteId)
        apiService.enviarSolicitud(request).enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                if (response.isSuccessful) {
                    val messageResponse = response.body()
                    // Manejar la respuesta
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                // Manejar el error
            }
        })
    }

    fun responderSolicitud(id: Int, estado: String, receptorId: Int) {
        val request = ResponderSolicitudRequest(estado, receptorId)
        apiService.responderSolicitud(id, request).enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                if (response.isSuccessful) {
                    val messageResponse = response.body()
                    // Manejar la respuesta
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                // Manejar el error
            }
        })
    }

    fun solicitudesEnviadas(solicitanteId: Int) {
        apiService.solicitudesEnviadas(solicitanteId).enqueue(object : Callback<List<SolicitudResponse>> {
            override fun onResponse(call: Call<List<SolicitudResponse>>, response: Response<List<SolicitudResponse>>) {
                if (response.isSuccessful) {
                    val solicitudes = response.body()
                    // Manejar la respuesta
                }
            }

            override fun onFailure(call: Call<List<SolicitudResponse>>, t: Throwable) {
                // Manejar el error
            }
        })
    }

    fun solicitudesRecibidas(receptorId: Int) {
        apiService.solicitudesRecibidas(receptorId).enqueue(object : Callback<List<SolicitudResponse>> {
            override fun onResponse(call: Call<List<SolicitudResponse>>, response: Response<List<SolicitudResponse>>) {
                if (response.isSuccessful) {
                    val solicitudes = response.body()
                    // Manejar la respuesta
                }
            }

            override fun onFailure(call: Call<List<SolicitudResponse>>, t: Throwable) {
                // Manejar el error
            }
        })
    }
}
