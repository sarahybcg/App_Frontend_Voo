package com.voo.bustracker.voo_app_frontend.ui.view.profile.driver

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusAlert
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun SendFriendRequest(
    onSendRequest: (String) -> Unit,
    navController: NavHostController
) {
    var username by remember { mutableStateOf("") }
    var showConfirmation by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "AGREGA AL PROPIETARIO",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Teléfono") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            IconButton(onClick = { /* Search Action */ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(150.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Usa un icono personalizado si lo tienes
                Icon(
                    imageVector = Icons.Default.BusAlert,
                    contentDescription = "Bus Icon",
                    modifier = Modifier.size(48.dp)
                )
                Text(
                    text = "Placa\nLínea\nPropietario",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { /* Acción de cancelar */ },
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Cancel,
                    contentDescription = "Cancel",
                    tint = Color.Red,
                    modifier = Modifier.size(48.dp)
                )
            }

            IconButton(
                onClick = {
                    if (username.isNotBlank()) {
                        onSendRequest(username)
                        showConfirmation = true
                    }
                },
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Check",
                    tint = Color.Green,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        if (showConfirmation) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "¡Solicitud enviada a $username!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}



