package com.voo.bustracker.voo_app_frontend.ui.view.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.voo.bustracker.voo_app_frontend.components.login.Button
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState

@Composable
fun HomeDriver() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Ícono en la parte superior
        Icon(
            imageVector = Icons.Filled.DirectionsCar,
            contentDescription = "Car Icon",
            modifier = Modifier
                .padding(16.dp)
                .size(48.dp)
        )

        // Mapa de Mapbox
        Box(modifier = Modifier.weight(1f)) {
            MapboxMap(
                Modifier.fillMaxSize(),
                mapViewportState = rememberMapViewportState {
                    setCameraOptions {
                        zoom(2.0)
                        center(Point.fromLngLat(-98.0, 39.5)) // Usa la clase Point
                        pitch(0.0)
                        bearing(0.0)
                    }
                },
            )
        }

        // Botones en la parte inferior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Espaciado alrededor de los botones
                .padding(bottom = 16.dp), // Espaciado adicional en la parte inferior
            horizontalArrangement = Arrangement.SpaceEvenly // Espaciado entre los botones
        ) {
            Button(
                text = "Iniciar Viaje",
                onClick = { /* Lógica para iniciar viaje */ },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp) // Ajusta la altura del botón
                    .padding(end = 8.dp) // Espaciado entre los botones
            )
            Button(
                text = "Terminar Viaje",
                onClick = { /* Lógica para terminar viaje */ },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp) // Ajusta la altura del botón
                    .padding(start = 8.dp) // Espaciado entre los botones
            )
        }
    }
}

