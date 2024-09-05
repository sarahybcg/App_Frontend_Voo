package com.voo.bustracker.voo_app_frontend.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.voo.bustracker.voo_app_frontend.R

class OnboardingViewModel : ViewModel() {
    var currentPage by mutableStateOf(0)
        private set

    data class OnboardingPage(val text: String, val imageRes: Int)

    private val passengerPages = listOf(
        OnboardingPage(
            "Bienvenido a la aplicación de pasajeros. Aquí puedes encontrar tu ruta de bus.",
            R.drawable.passenger_image_1
        ),
        OnboardingPage(
            "Consulta los horarios de los buses en tiempo real.",
            R.drawable.passenger_image_2
        ),
        OnboardingPage(
            "Recibe notificaciones sobre cualquier cambio en tu ruta.",
            R.drawable.passenger_image_3
        )
    )

    private val driverPages = listOf(
        OnboardingPage(
            "Bienvenido a la aplicación de conductores. Aquí puedes gestionar tus rutas.",
            R.drawable.driver_image_1
        ),
        OnboardingPage(
            "Consulta tus horarios de trabajo y disponibilidad.",
            R.drawable.driver_image_2
        ),
        OnboardingPage(
            "Recibe notificaciones y actualizaciones de tu servicio.",
            R.drawable.driver_image_3
        )
    )

    fun nextPage() {
        if (currentPage < getCurrentPages().size - 1) {
            currentPage++
        }
    }

    fun previousPage() {
        if (currentPage > 0) {
            currentPage--
        }
    }

    fun reset() {
        currentPage = 0
    }

    fun getCurrentPages(isPassenger: Boolean = true): List<OnboardingPage> {
        return if (isPassenger) passengerPages else driverPages
    }
}