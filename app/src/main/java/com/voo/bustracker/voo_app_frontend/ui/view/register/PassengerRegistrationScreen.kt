package com.voo.bustracker.voo_app_frontend.ui.view.register

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.voo.bustracker.voo_app_frontend.components.login.Button
import com.voo.bustracker.voo_app_frontend.components.register.CountryCodeDropdown
import com.voo.bustracker.voo_app_frontend.components.register.DatePickerComponent
import com.voo.bustracker.voo_app_frontend.components.register.TextFieldComponent
import com.voo.bustracker.voo_app_frontend.ui.theme.DarkBlue
import com.voo.bustracker.voo_app_frontend.ui.theme.White

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun PassengerRegistrationScreen(navController: NavHostController) {
//    val viewModel: PassengerViewModel = viewModel()
//
//    Scaffold(
//        containerColor = White
//    ) {
//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(28.dp),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(text = "Únete a VOO", color = DarkBlue)
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Row para Nombre y Apellido en la misma fila
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    TextFieldComponent(
//                        value = viewModel.datosPrincipales.value.nombre,
//                        onValueChange = { viewModel.updateDatosPrincipales(viewModel.datosPrincipales.value.copy(nombre = it)) },
//                        label = "Nombre",
//                        keyboardType = KeyboardType.Text,
//                        modifier = Modifier
//                            .weight(1f)
//                            .padding(end = 8.dp) // Espacio entre campos
//                    )
//
//                    TextFieldComponent(
//                        value = viewModel.datosPrincipales.value.apellido,
//                        onValueChange = { viewModel.updateDatosPrincipales(viewModel.datosPrincipales.value.copy(apellido = it)) },
//                        label = "Apellido",
//                        keyboardType = KeyboardType.Text,
//                        modifier = Modifier
//                            .weight(1f)
//                            .padding(start = 8.dp) // Espacio entre campos
//                    )
//                }
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Menú desplegable del código de país
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    CountryCodeDropdown(
//                        countryCodes = viewModel.countryCodes,
//                        selectedCountryCode = viewModel.selectedCountryCode.value,
//                        onCountryCodeSelected = { viewModel.selectCountryCode(it) },
//                    )
//
//                    TextFieldComponent(
//                        value = viewModel.datosPrincipales.value.telefono_,
//                        onValueChange = { viewModel.updateDatosPrincipales(viewModel.datosPrincipales.value.copy(telefono_ = it)) },
//                        label = "Teléfono",
//                        keyboardType = KeyboardType.Phone,
//                        modifier = Modifier
//                            .fillMaxWidth() // El campo de teléfono ocupa el resto del espacio
//                    )
//                }
//                Spacer(modifier = Modifier.height(16.dp))
//
//                DatePickerComponent(
//                    label = "Fecha de Nacimiento",
//                    date = viewModel.stringToCalendar(viewModel.datosPrincipales.value.fechaNacimiento), // Convertir de String a Calendar
//                    onDateChange = { newDate ->
//                        viewModel.updateDatosPrincipales(
//                            viewModel.datosPrincipales.value.copy(
//                                fechaNacimiento = viewModel.calendarToString(newDate) // Convertir de Calendar a String
//                            )
//                        )
//                    }
//                )
//
//
//                TextFieldComponent(
//                    value = viewModel.datosPrincipales.value.CI_,
//                    onValueChange = { viewModel.updateDatosPrincipales(viewModel.datosPrincipales.value.copy(CI_ = it)) },
//                    label = "Cédula",
//                    keyboardType = KeyboardType.Number,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(end = 8.dp) // Padding opcional para ajustar espacio
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//
//                TextFieldComponent(
//                    value = viewModel.datosPrincipales.value.clave,
//                    onValueChange = { viewModel.updateDatosPrincipales(viewModel.datosPrincipales.value.copy(clave = it)) },
//                    label = "Contraseña",
//                    keyboardType = KeyboardType.Password,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(end = 8.dp) // Padding opcional para ajustar espacio
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Botón de registro
//                Button(
//                    onClick = {
//                        viewModel.onPassenger()
//                    },
//                    text = "VAMOS"
//                )
//            }
//        }
//    }
//}