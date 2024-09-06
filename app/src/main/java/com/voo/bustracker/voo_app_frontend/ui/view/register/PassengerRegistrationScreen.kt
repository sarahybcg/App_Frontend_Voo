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
import com.voo.bustracker.voo_app_frontend.viewmodel.PassengerViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PassengerRegistrationScreen(navController: NavHostController) {
    val viewModel: PassengerViewModel = viewModel()

    Scaffold(
        containerColor = White
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(28.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Únete a VOO", color = DarkBlue)

                Spacer(modifier = Modifier.height(16.dp))

                // Row para Nombre y Apellido en la misma fila
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextFieldComponent(
                        value = viewModel.userInput.value.name,
                        onValueChange = { viewModel.updateUserInput(viewModel.userInput.value.copy(name = it)) },
                        label = "Nombre",
                        keyboardType = KeyboardType.Text,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp) // Espacio entre campos
                    )

                    TextFieldComponent(
                        value = viewModel.userInput.value.surname,
                        onValueChange = { viewModel.updateUserInput(viewModel.userInput.value.copy(surname = it)) },
                        label = "Apellido",
                        keyboardType = KeyboardType.Text,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp) // Espacio entre campos
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Menú desplegable del código de país
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CountryCodeDropdown(
                        countryCodes = viewModel.countryCodes,
                        selectedCountryCode = viewModel.selectedCountryCode.value,
                        onCountryCodeSelected = { viewModel.selectCountryCode(it) },
                    )

                    TextFieldComponent(
                        value = viewModel.userInput.value.phone,
                        onValueChange = { viewModel.updateUserInput(viewModel.userInput.value.copy(phone = it)) },
                        label = "Teléfono",
                        keyboardType = KeyboardType.Phone,
                        modifier = Modifier
                            .fillMaxWidth() // El campo de teléfono ocupa el resto del espacio
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                DatePickerComponent(
                    label = "Fecha de Nacimiento",
                    date = viewModel.userInput.value.birthDate,
                    onDateChange = { viewModel.updateUserInput(viewModel.userInput.value.copy(birthDate = it)) }
                )

                TextFieldComponent(
                    value = viewModel.userInput.value.id,
                    onValueChange = { viewModel.updateUserInput(viewModel.userInput.value.copy(id = it)) },
                    label = "Cédula",
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp) // Padding opcional para ajustar espacio
                )
                Spacer(modifier = Modifier.height(16.dp))

                TextFieldComponent(
                    value = viewModel.userInput.value.password,
                    onValueChange = { viewModel.updateUserInput(viewModel.userInput.value.copy(password = it)) },
                    label = "Contraseña",
                    keyboardType = KeyboardType.Password,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp) // Padding opcional para ajustar espacio
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Botón de registro
                Button(
                    onClick = {
                        viewModel.onPassenger()
                    },
                    text = "VAMOS"
                )
            }
        }
    }
}