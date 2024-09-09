package com.voo.bustracker.voo_app_frontend.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.voo.bustracker.voo_app_frontend.model.entities.CountryCode
import com.voo.bustracker.voo_app_frontend.model.entities.DatosPrincipales
import com.voo.bustracker.voo_app_frontend.utils.CountryCodeData
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class PassengerViewModel : ViewModel() {
    var datosPrincipales = mutableStateOf(DatosPrincipales(CI_="", nombre="", apellido="", telefono_="", fechaNacimiento="", clave="", role="",))
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    // Convierte de Calendar a String
    fun calendarToString(calendar: Calendar): String {
        return dateFormat.format(calendar.time)
    }

    // Convierte de String a Calendar
    fun stringToCalendar(date: String): Calendar {
        val calendar = Calendar.getInstance()
        try {
            calendar.time = dateFormat.parse(date) ?: Calendar.getInstance().time
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return calendar
    }

    fun updateDatosPrincipales(newDatosPrincipales: DatosPrincipales) {
        datosPrincipales.value = newDatosPrincipales
    }

    var selectedCountryCode = mutableStateOf<CountryCode?>(null)

    val countryCodes = CountryCodeData.countryCodes

    fun selectCountryCode(countryCode: CountryCode) {
        selectedCountryCode.value = countryCode
    }

    fun onPassenger() {

    }
}