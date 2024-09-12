package com.voo.bustracker.voo_app_frontend.viewmodel


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voo.bustracker.voo_app_frontend.model.entities.*
import com.voo.bustracker.voo_app_frontend.model.repository.UserRepository
import com.voo.bustracker.voo_app_frontend.utils.CountryCodeData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DriverViewModel(private val userRepository: UserRepository) : ViewModel() {

    var datosPrincipales = mutableStateOf(
        DatosPrincipales(
            CI_ = "",
            nombre = "",
            apellido = "",
            telefono_ = "",
            fechaNacimiento = "",
            clave = "",
            role = "conductor"
        )
    )

    var datosAdicionales = mutableStateOf(DatosAdicionales(licenciaConducir = ""))

    private val _registrationResult = MutableStateFlow<Result<RegisterResponse>?>(null)
    val registrationResult: StateFlow<Result<RegisterResponse>?> get() = _registrationResult


    fun updateFechaNacimiento(dateString: String) {
        val formattedDate = formatDateString(dateString)
        datosPrincipales.value = datosPrincipales.value.copy(fechaNacimiento = formattedDate)
    }

    private fun formatDateString(dateString: String): String {
        // Ajustar la longitud de la entrada
        val trimmedDate = dateString.take(10)
        val regex = Regex("\\d{4}/\\d{2}/\\d{2}")

        // Aplicar formato en caso de que sea válido
        return if (regex.matches(trimmedDate) || trimmedDate.isEmpty()) {
            trimmedDate
        } else {
            datosPrincipales.value.fechaNacimiento
        }
    }

    fun calendarToString(calendar: Calendar): String {
        val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return format.format(calendar.time)
    }

    fun stringToCalendar(date: String): Calendar {
        val calendar = Calendar.getInstance()
        return try {
            val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
            calendar.time = format.parse(date) ?: Calendar.getInstance().time
            calendar
        } catch (e: ParseException) {
            // Manejo mejorado de excepciones
            Log.e("DriverViewModel", "Error al parsear la fecha: $date", e)
            calendar // Retorna el calendario actual si ocurre un error
        }
    }


    var selectedCountryCode = mutableStateOf<CountryCode?>(null)
    val countryCodes = CountryCodeData.countryCodes

    fun selectCountryCode(countryCode: CountryCode) {
        selectedCountryCode.value = countryCode
    }

    fun updateDatosPrincipales(newDatosPrincipales: DatosPrincipales) {
        datosPrincipales.value = newDatosPrincipales
    }

    fun updateDatosAdicionales(newDatosAdicionales: DatosAdicionales) {
        datosAdicionales.value = newDatosAdicionales
    }

    fun onDriver() {
        val datos = datosPrincipales.value.copy(role = "conductor")
        viewModelScope.launch {
            try {
                println("Datos a enviar: $datos")
                println("Datos adicionales a enviar: ${datosAdicionales.value}")
                val response = userRepository.registerUser(
                    RegisterRequest(
                        datosPrincipales = datos,
                        datosAdicionales = datosAdicionales.value
                    )
                )
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.success) {
                        _registrationResult.value = Result.success(apiResponse)
                        println("Registro exitoso: ${apiResponse.message}")
                    } else {
                        _registrationResult.value =
                            Result.failure(Throwable(apiResponse?.message ?: "Error desconocido"))
                        println("Error en el registro: ${apiResponse?.message}")
                    }
                } else {
                    _registrationResult.value = Result.failure(Throwable("Error en la respuesta: ${response.code()}"))
                    println("Error en la respuesta: ${response.code()}")
                }
            } catch (e: Exception) {
                _registrationResult.value = Result.failure(e)
                println("Error de excepción: ${e.message}")
            }
        }
    }
}
