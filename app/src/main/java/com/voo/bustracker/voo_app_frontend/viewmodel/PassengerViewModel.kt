//package com.voo.bustracker.voo_app_frontend.viewmodel
//
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import com.voo.bustracker.voo_app_frontend.model.entities.*
//import com.voo.bustracker.voo_app_frontend.network.RetrofitClient
//import com.voo.bustracker.voo_app_frontend.utils.CountryCodeData
//import retrofit2.Call
//import java.text.ParseException
//import java.text.SimpleDateFormat
//import java.util.*
//
//
//class PassengerViewModel : ViewModel() {
//    var datosPrincipales = mutableStateOf(
//        DatosPrincipales(
//            CI_ = "",
//            nombre = "",
//            apellido = "",
//            telefono_ = "",
//            fechaNacimiento = "",
//            clave = "",
//            role = "pasajero", // Establecer el rol como "pasajero"
//        )
//    )
//        private set
//
//    var errorMessage = mutableStateOf<String?>(null)
//        private set
//
//    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//
//    // Convierte de Calendar a String
//    fun calendarToString(calendar: Calendar): String {
//        return dateFormat.format(calendar.time)
//    }
//
//    // Convierte de String a Calendar
//    fun stringToCalendar(date: String): Calendar {
//        val calendar = Calendar.getInstance()
//        try {
//            calendar.time = dateFormat.parse(date) ?: Calendar.getInstance().time
//        } catch (e: ParseException) {
//            e.printStackTrace()
//        }
//        return calendar
//    }
//
//    fun updateDatosPrincipales(newDatosPrincipales: DatosPrincipales) {
//        datosPrincipales.value = newDatosPrincipales
//    }
//
//    var selectedCountryCode = mutableStateOf<CountryCode?>(null)
//
//    val countryCodes = CountryCodeData.countryCodes
//
//    fun selectCountryCode(countryCode: CountryCode) {
//        selectedCountryCode.value = countryCode
//    }
//
//    fun onPassenger() {
//        // Asignar el rol "pasajero" antes de enviar los datos
//        val datos = datosPrincipales.value.copy(role = "pasajero")
//
//        // Aquí llamas a la API enviando los datos
//        enviarDatosAlaApi(datos)
//    }
//
//    fun enviarDatosAlaApi(datosPrincipales: DatosPrincipales) {
//        val request = RegisterRequest(
//            datosPrincipales = datosPrincipales
//        )
//
//        RetrofitClient.instance.registrarUsuario(request).enqueue(object : retrofit2.Callback<ApiResponse> {
//            override fun onResponse(call: Call<RegisterResponse>, response: retrofit2.Response<ApiResponse>) {
//                if (response.isSuccessful) {
//                    val apiResponse = response.body()
//                    if (apiResponse != null && apiResponse.success) {
//                        // Registro exitoso
//                        println("Registro exitoso: ${apiResponse.message}")
//                    } else {
//                        // Mostrar el mensaje de error
//                        println("Error en el registro: ${apiResponse?.message}")
//                    }
//                } else {
//                    println("Error en la respuesta: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                println("Error: ${t.message}")
//            }
//        })
//    }
