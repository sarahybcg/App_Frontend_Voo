//package com.voo.bustracker.voo_app_frontend.viewmodel
//
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import com.voo.bustracker.voo_app_frontend.model.entities.*
//import com.voo.bustracker.voo_app_frontend.network.RetrofitClient
//import com.voo.bustracker.voo_app_frontend.utils.CountryCodeData
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import java.text.ParseException
//import java.text.SimpleDateFormat
//import java.util.*
//
//class DriverViewModel : ViewModel() {
//    var datosPrincipales = mutableStateOf(DatosPrincipales(CI_="", nombre="", apellido="", telefono_="", fechaNacimiento="", clave="", role="",))
//        private set
//
//    var datosAdicionales = mutableStateOf(DatosAdicionales(licenciaConducir=""))
//        private set
//
//    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//
//    fun calendarToString(calendar: Calendar): String {
//        return dateFormat.format(calendar.time)
//    }
//
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
//    fun updateDatosAdicionales(newDatosAdicionales: DatosAdicionales) {
//        datosAdicionales.value = newDatosAdicionales
//    }
//
//    var selectedCountryCode = mutableStateOf<CountryCode?>(null)
//    val countryCodes = CountryCodeData.countryCodes
//
//    fun selectCountryCode(countryCode: CountryCode) {
//        selectedCountryCode.value = countryCode
//    }
//
//    private fun validateDatosPrincipales(): Boolean {
//        val datos = datosPrincipales.value
//        return datos.nombre.isNotBlank() &&
//                datos.apellido.isNotBlank() &&
//                datos.telefono_.isNotBlank() &&
//                datos.fechaNacimiento.isNotBlank() &&
//                datos.CI_.isNotBlank() &&
//                datos.clave.isNotBlank() &&
//                datos.role.isNotBlank()
//    }
//
//    private fun validateDatosAdicionales(): Boolean {
//        return datosAdicionales.value.licenciaConducir != null
//    }
//
//    fun submitRegistration(onSuccess: () -> Unit, onError: (String) -> Unit) {
//        if (!validateDatosPrincipales()) {
//            onError("Por favor, complete todos los campos principales correctamente.")
//            return
//        }
//
//        if (!validateDatosAdicionales()) {
//            onError("Por favor, proporcione la licencia de conducir.")
//            return
//        }
//
//        val registrationRequest = RegistrationRequest(
//            datosPrincipales = datosPrincipales.value,
//            datosAdicionales = datosAdicionales.value
//        )
//
//        RetrofitClient.apiService.(registrationRequest).enqueue(object : Callback<RegistrationResponse> {
//            override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
//                if (response.isSuccessful) {
//                    val registrationResponse = response.body()
//                    if (registrationResponse?.success == true) {
//                        onSuccess()
//                    } else {
//                        onError(registrationResponse?.message ?: "Error desconocido")
//                    }
//                } else {
//                    onError("Error en la comunicación con el servidor: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
//                onError("No se pudo conectar al servidor: ${t.localizedMessage}")
//            }
//        })
//    }
//}
