package com.voo.bustracker.voo_app_frontend.ui.view.profile.driver


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.voo.bustracker.voo_app_frontend.model.entities.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.voo.bustracker.voo_app_frontend.viewmodel.SolicitudViewModel

@Composable
fun BuscarUsuarioScreen(viewModel: SolicitudViewModel = viewModel()) {
    var telefono by remember { mutableStateOf("") }

    // Observando el estado del ViewModel
    val userResponse by viewModel.userResponse.observeAsState()
    val errorMessage by viewModel.error.observeAsState()
    val isSearching by viewModel.isSearching.observeAsState(false)  // Observando el estado de carga

    Column(modifier = Modifier.padding(16.dp)) {
        // Barra de búsqueda con botón
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Buscar por teléfono") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                trailingIcon = {
                    if (isSearching) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Botón de búsqueda
            Button(
                onClick = { viewModel.buscarUsuarioPorTelefono(telefono) },
                enabled = telefono.isNotBlank(),
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text("Buscar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Verificar si el usuario existe y mostrar la tarjeta
        userResponse?.data?.let { user ->
            UserCard(user = user, onSendRequestClick = {
                // Lógica para enviar solicitud
            }, onDismissClick = {
                // Lógica para cerrar la tarjeta
            })
        }

        // Mostrar error si existe
        errorMessage?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun UserCard(user: UserData, onSendRequestClick: () -> Unit, onDismissClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)  // Corregido el tipo de elevación
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Nombre: ${user.nombre}", fontSize = 20.sp)
            Text(text = "Apellido: ${user.apellido}", fontSize = 18.sp)
            Text(text = "Teléfono: ${user.telefono}", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para enviar solicitud
            Button(
                onClick = onSendRequestClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enviar Solicitud")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para cerrar la tarjeta
            IconButton(onClick = onDismissClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Cerrar")
            }
        }
    }
}

