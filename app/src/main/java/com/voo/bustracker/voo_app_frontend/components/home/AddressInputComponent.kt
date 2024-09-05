package com.voo.bustracker.voo_app_frontend.components.home

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import android.widget.Toast

fun getLastKnownLocation(context: Context): Location? {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    // Verifica si se tienen permisos para acceder a la ubicación
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // Si no se tienen permisos, muestra un mensaje al usuario o solicita permisos
        Toast.makeText(context, "Permiso de ubicación no concedido", Toast.LENGTH_LONG).show()
        return null
    }

    // Obtén la última ubicación conocida usando diferentes proveedores
    return try {
        // Puedes cambiar entre GPS_PROVIDER, NETWORK_PROVIDER, etc.
        val gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        // Devuelve la mejor ubicación disponible
        gpsLocation ?: networkLocation
    } catch (e: SecurityException) {
        // Maneja el caso cuando no se tiene permiso para acceder a la ubicación
        Toast.makeText(context, "Error al acceder a la ubicación: ${e.message}", Toast.LENGTH_LONG).show()
        null
    }
}
