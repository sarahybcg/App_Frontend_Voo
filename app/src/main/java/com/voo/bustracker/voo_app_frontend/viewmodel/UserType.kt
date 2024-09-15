package com.voo.bustracker.voo_app_frontend.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.voo.bustracker.voo_app_frontend.model.entities.DatosPrincipales
import com.voo.bustracker.voo_app_frontend.model.entities.LoginResponse
import com.voo.bustracker.voo_app_frontend.navigation.Screen
import com.voo.bustracker.voo_app_frontend.network.RetrofitClient
import com.voo.bustracker.voo_app_frontend.utils.SessionManager
import kotlinx.coroutines.launch
import retrofit2.Response

enum class UserType {
    PASSENGER, DRIVER
}

class LoginViewModel(private val sessionManager: SessionManager) : ViewModel() {
    var userInput = mutableStateOf(DatosPrincipales(CI_="", nombre="", apellido="", telefono_="", fechaNacimiento="", clave="", role="",))
        private set

    var selectedUserType = mutableStateOf(UserType.PASSENGER)
        private set

    var loginError = mutableStateOf<String?>(null)
        private set

    fun updatePhone(newPhone: String) {
        userInput.value = userInput.value.copy(telefono_ = newPhone)
    }

    fun updatePassword(newPassword: String) {
        userInput.value = userInput.value.copy(clave = newPassword)
    }

    fun setUserType(userType: UserType) {
        selectedUserType.value = userType
    }

    fun onLogin(navController: NavHostController) {
        viewModelScope.launch {
            loginError.value = null  // Clear previous errors
            try {
                // Llamada a la API de login
                val response = loginUser(userInput.value)

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    val token = loginResponse?.token
                    if (token != null) {
                        sessionManager.saveAuthToken(token)
                        // Navegar a la pantalla principal
                        navController.navigate(Screen.BuscarUsuario.route)
                    } else {
                        loginError.value = "Token no recibido del servidor."
                    }
                } else {
                    // Manejar errores HTTP
                    loginError.value = when (response.code()) {
                        401 -> "Credenciales inválidas."
                        403 -> "Rol no autorizado."
                        404 -> "Usuario no encontrado."
                        else -> "Error en la autenticación."
                    }
                }
            } catch (e: Exception) {
                // Manejar errores de red o excepciones
                Log.e("LoginViewModel", "Error en el login: ${e.message}")
                loginError.value = "Error de red. Por favor, inténtalo de nuevo."
            }
        }
    }

    private suspend fun loginUser(userInput: DatosPrincipales): Response<LoginResponse> {
        // Llamar a tu API de login y devolver la respuesta
        return RetrofitClient.create().login(userInput)
    }
}
