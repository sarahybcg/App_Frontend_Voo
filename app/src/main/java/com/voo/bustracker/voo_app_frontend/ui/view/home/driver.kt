package com.voo.bustracker.voo_app_frontend.ui.view.home

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.locationcomponent.location

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("MissingPermission")
@Composable
fun HomeDriver() {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    var isLocationPermissionGranted by remember { mutableStateOf(false) }

    // Solicitar permisos
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        isLocationPermissionGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
    }

    LaunchedEffect(Unit) {
        // Solicitar permisos de ubicación
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    if (isLocationPermissionGranted) {
        AndroidView(
            factory = {
                mapView.apply {
                    getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS) { style ->
                        // Centramos la cámara en Barquisimeto (9.9281° N, 69.3175° W)
                        val barquisimetoLocation = Point.fromLngLat(-69.3175, 9.9281)
                        getMapboxMap().setCamera(
                            CameraOptions.Builder()
                                .center(barquisimetoLocation)
                                .zoom(14.0)
                                .build()
                        )

                        // Activar el plugin de ubicación
                        val locationPlugin = location
                        locationPlugin.updateSettings {
                            // No necesitamos configurar `enabled` o `pulsingEnabled`
                            // El plugin de ubicación se encarga de la visualización de la posición
                        }

                        // Escuchar los cambios en la ubicación del usuario
                        locationPlugin.addOnIndicatorPositionChangedListener { loc ->
                            // Cuando la ubicación cambia, centramos la cámara en la nueva ubicación
                            getMapboxMap().setCamera(
                                CameraOptions.Builder()
                                    .center(Point.fromLngLat(loc.longitude(), loc.latitude()))
                                    .zoom(14.0)
                                    .build()
                            )
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    } else {
        // Manejar el caso en el que no se han concedido los permisos
    }
}

