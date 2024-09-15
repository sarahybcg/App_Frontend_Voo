package com.voo.bustracker.voo_app_frontend.ui.view.register


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.voo.bustracker.voo_app_frontend.components.register.CountryCodeDropdown
import com.voo.bustracker.voo_app_frontend.components.register.DatePickerComponent
import com.voo.bustracker.voo_app_frontend.components.register.ImageUploadComponent
import com.voo.bustracker.voo_app_frontend.components.register.TextFieldComponent
import com.voo.bustracker.voo_app_frontend.model.entities.DatosAdicionales
import com.voo.bustracker.voo_app_frontend.model.repository.UserRepository
import com.voo.bustracker.voo_app_frontend.navigation.Screen
import com.voo.bustracker.voo_app_frontend.ui.theme.DarkBlue
import com.voo.bustracker.voo_app_frontend.ui.theme.White
import com.voo.bustracker.voo_app_frontend.viewmodel.DriverViewModel
import com.voo.bustracker.voo_app_frontend.viewmodel.DriverViewModelFactory


@Composable
fun DriverRegistrationScreen(navController: NavHostController, userRepository: UserRepository) {
        val viewModel: DriverViewModel = viewModel(
            factory = DriverViewModelFactory(userRepository)
        )
        val scrollState = rememberScrollState()
        val registrationResult by viewModel.registrationResult.collectAsState()

//        LaunchedEffect(registrationResult) {
//            registrationResult?.let { result ->
//                if (result.isSuccess) {
//                    navController.navigate(Screen.SolicitudesRecibidas.route)
//                } else {
//                    val errorMessage = result.exceptionOrNull()?.message ?: "Error desconocido"
//                    println("Error: $errorMessage")
//                }
//            }
//        }
    LaunchedEffect(registrationResult) {
        registrationResult?.let { result ->
            if (result.isSuccess) {
                // Navegar a la pantalla de buscar propietario
                navController.navigate("buscar_propietario") {
                    popUpTo("driver_registration") { inclusive = true }
                }
            } else {
                val errorMessage = result.exceptionOrNull()?.message ?: "Error desconocido"
                println("Error: $errorMessage")
            }
        }
    }

    Scaffold(
        containerColor = White
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(it) // Asegura que el padding del Scaffold no se recorte
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState), // Hacer la columna desplazable
                verticalArrangement = Arrangement.spacedBy(16.dp), // Espaciado entre elementos
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Unete a VOO", color = DarkBlue)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextFieldComponent(
                        value = viewModel.datosPrincipales.value.nombre,
                        onValueChange = { viewModel.updateDatosPrincipales(viewModel.datosPrincipales.value.copy(nombre = it)) },
                        label = "Nombre",
                        keyboardType = KeyboardType.Text,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp) // Espacio entre campos
                    )

                    TextFieldComponent(
                        value = viewModel.datosPrincipales.value.apellido,
                        onValueChange = { viewModel.updateDatosPrincipales(viewModel.datosPrincipales.value.copy(apellido = it)) },
                        label = "Apellido",
                        keyboardType = KeyboardType.Text,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp) // Espacio entre campos
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CountryCodeDropdown(
                        countryCodes = viewModel.countryCodes,
                        selectedCountryCode = viewModel.selectedCountryCode.value,
                        onCountryCodeSelected = { viewModel.selectCountryCode(it) }
                    )

                    TextFieldComponent(
                        value = viewModel.datosPrincipales.value.telefono_,
                        onValueChange = { viewModel.updateDatosPrincipales(viewModel.datosPrincipales.value.copy(telefono_ = it)) },
                        label = "Teléfono",
                        keyboardType = KeyboardType.Phone,
                        modifier = Modifier.weight(1f)
                    )
                }

                DatePickerComponent(
                    label = "Fecha de Nacimiento",
                    date = viewModel.stringToCalendar(viewModel.datosPrincipales.value.fechaNacimiento),
                    onDateChange = { newDate ->
                        viewModel.updateDatosPrincipales(
                            viewModel.datosPrincipales.value.copy(
                                fechaNacimiento = viewModel.calendarToString(newDate)
                            )
                        )
                    }
                )
                TextFieldComponent(
                    value = viewModel.datosPrincipales.value.CI_,
                    onValueChange = { viewModel.updateDatosPrincipales(viewModel.datosPrincipales.value.copy(CI_ = it)) },
                    label = "Cédula",
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier.fillMaxWidth()
                )

                ImageUploadComponent(
                    onImageSelected = { uri ->
                        uri?.let {
                            val newDatosAdicionales = DatosAdicionales(licenciaConducir = it.toString())
                            viewModel.updateDatosAdicionales(newDatosAdicionales)
                        }
                    },
                    buttonText = "Carnet de conducir"
                )

                TextFieldComponent(
                    value = viewModel.datosPrincipales.value.clave,
                    onValueChange = { viewModel.updateDatosPrincipales(viewModel.datosPrincipales.value.copy(clave = it)) },
                    label = "Contraseña",
                    keyboardType = KeyboardType.Password
                )

                Button(onClick = { viewModel.onDriver() }) {
                    Text("Registrar")
                }
            }
        }
    }
}




