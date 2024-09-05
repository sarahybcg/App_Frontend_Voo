package com.voo.bustracker.voo_app_frontend.ui.view.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.voo.bustracker.voo_app_frontend.R
import com.voo.bustracker.voo_app_frontend.viewmodel.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.voo.bustracker.voo_app_frontend.components.login.Button
import com.voo.bustracker.voo_app_frontend.components.login.InputType
import com.voo.bustracker.voo_app_frontend.components.login.textInput
import com.voo.bustracker.voo_app_frontend.navigation.Screen
import com.voo.bustracker.voo_app_frontend.ui.theme.DarkBlue
import com.voo.bustracker.voo_app_frontend.ui.theme.White
import com.voo.bustracker.voo_app_frontend.ui.theme.BlueGrey

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel: LoginViewModel = viewModel()
    val context = LocalContext.current
    val passwordFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val userInput by viewModel.userInput

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
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo de la aplicación
                Icon(
                    painter = painterResource(id = R.drawable.voologoblc),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp),
                    tint = DarkBlue
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campo de teléfono
                textInput(
                    inputType = InputType.Phone,
                    value = userInput.phone,
                    onValueChange = { viewModel.updatePhone(it) },
                    keyboardActions = KeyboardActions(onNext = {
                        passwordFocusRequester.requestFocus()
                    })
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campo de  contraseña
                textInput(
                    inputType = InputType.Password,
                    value = userInput.password,
                    onValueChange = { viewModel.updatePassword(it) },
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),
                    focusRequester = passwordFocusRequester
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botón de inicio de sesión
                Button(
                    onClick = {
                        viewModel.onLogin()
                    },
                    text = "VAMOS"
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Botón de registro de usuario
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("¿Tienes una cuenta?", color = BlueGrey)
                    TextButton(onClick = {navController.navigate(Screen.Selection.route)}) {
                        Text("REGISTRARME", color = DarkBlue)
                    }
                }
            }
        }
    }
}