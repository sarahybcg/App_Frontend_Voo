package com.voo.bustracker.voo_app_frontend.ui.view.register


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.voo.bustracker.voo_app_frontend.navigation.Screen
import com.voo.bustracker.voo_app_frontend.ui.theme.DarkBlue
import com.voo.bustracker.voo_app_frontend.ui.theme.White
import com.voo.bustracker.voo_app_frontend.viewmodel.DriverViewModel


@Composable
fun DriverRegistrationScreen(navController: NavHostController) {
    val viewModel: DriverViewModel = viewModel()
    // Crear un estado de desplazamiento
    val scrollState = rememberScrollState()

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
                        value = viewModel.userInput.value.phone,
                        onValueChange = { viewModel.updateUserInput(viewModel.userInput.value.copy(phone = it)) },
                        label = "Teléfono",
                        keyboardType = KeyboardType.Phone,
                        modifier = Modifier.weight(1f)
                    )
                }

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
                    isError = viewModel.fieldErrors.value.idError != null,
                    errorMessage = viewModel.fieldErrors.value.idError,
                    modifier = Modifier.fillMaxWidth()
                )

                ImageUploadComponent(
                    onImageSelected = { uri ->
                        uri?.let { viewModel.updateDrivingLicense(it.toString()) }
                    },
                    buttonText = "Carnet de conducir"
                )

                TextFieldComponent(
                    value = viewModel.userInput.value.password,
                    onValueChange = { viewModel.updateUserInput(viewModel.userInput.value.copy(password = it)) },
                    label = "Contraseña",
                    keyboardType = KeyboardType.Password,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        viewModel.onDriver()
                        navController.navigate(Screen.SendFriendRequest.route)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("VAMOS")
                }
            }
        }
    }
}

