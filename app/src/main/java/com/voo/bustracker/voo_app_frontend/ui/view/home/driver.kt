package com.voo.bustracker.voo_app_frontend.ui.view.home

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.locationcomponent.location
import com.voo.bustracker.voo_app_frontend.components.login.Button

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("MissingPermission")
@Composable
fun HomeDriver() {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    var isLocationPermissionGranted by remember { mutableStateOf(false) }
    var userLocation by remember { mutableStateOf<Point?>(null) }
    var isJourneyStarted by remember { mutableStateOf(false) }

    // Solicitar permisos
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        isLocationPermissionGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        )
    }

    if (isLocationPermissionGranted) {
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                factory = {
                    mapView.apply {
                        getMapboxMap().loadStyleUri(Style.TRAFFIC_DAY) { style ->
                            Log.d("Mapbox", "Estilo del mapa cargado")

                            val annotationPlugin = mapView.annotations
                            val pointAnnotationManager = annotationPlugin.createPointAnnotationManager()

                            val locationPlugin = location
                            locationPlugin.updateSettings {
                                enabled = true
                                pulsingEnabled = false
                            }

                            locationPlugin.addOnIndicatorPositionChangedListener { loc ->
                                Log.d("Mapbox", "Ubicación del usuario: Lat ${loc.latitude()}, Lon ${loc.longitude()}")
                                userLocation = Point.fromLngLat(loc.longitude(), loc.latitude())

                                // Ajuste del nivel de zoom
                                getMapboxMap().setCamera(
                                    CameraOptions.Builder()
                                        .center(userLocation!!)
                                        .zoom(16.0) // Ajusta el zoom aquí
                                        .build()
                                )
                            }

                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            // Botones "Empezar" y "Terminar" en la parte inferior, alineados horizontalmente
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    text = "Empezar",
                    onClick = { isJourneyStarted = true },
                    enabled = !isJourneyStarted, // Desactiva el botón si ya empezó el viaje
                    modifier = Modifier.weight(1f) // Divide el espacio de manera equitativa
                )

                Button(
                    text = "Terminar",
                    onClick = { isJourneyStarted = false },
                    enabled = isJourneyStarted, // Activa el botón cuando el viaje ha comenzado
                    modifier = Modifier.weight(1f) // Divide el espacio de manera equitativa
                )
            }
        }
    } else {
        // Manejar el caso en el que no se han concedido los permisos
    }
}
