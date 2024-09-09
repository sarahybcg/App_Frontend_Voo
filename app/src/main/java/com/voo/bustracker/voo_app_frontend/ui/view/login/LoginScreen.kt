package com.voo.bustracker.voo_app_frontend.ui.view.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.voo.bustracker.voo_app_frontend.R
import com.voo.bustracker.voo_app_frontend.components.login.Button
import com.voo.bustracker.voo_app_frontend.components.login.InputType
import com.voo.bustracker.voo_app_frontend.components.login.textInput
import com.voo.bustracker.voo_app_frontend.navigation.Screen
import com.voo.bustracker.voo_app_frontend.ui.theme.BlueGrey
import com.voo.bustracker.voo_app_frontend.ui.theme.DarkBlue
import com.voo.bustracker.voo_app_frontend.ui.theme.White
import com.voo.bustracker.voo_app_frontend.utils.SessionManager
import com.voo.bustracker.voo_app_frontend.viewmodel.LoginViewModel
import com.voo.bustracker.voo_app_frontend.viewmodel.LoginViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    val passwordFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val sessionManager = remember { SessionManager(context) }

    // Usa la fábrica para obtener la instancia del ViewModel
    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(sessionManager)
    )

    val userInput by viewModel.userInput
    val loginError by viewModel.loginError

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
                    value = userInput.telefono_,
                    onValueChange = { viewModel.updatePhone(it) },
                    keyboardActions = KeyboardActions(onNext = {
                        passwordFocusRequester.requestFocus()
                    })
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campo de contraseña
                textInput(
                    inputType = InputType.Password,
                    value = userInput.clave,
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
                        viewModel.onLogin(navController)
                    },
                    text = "VAMOS"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Muestra el error si existe
                if (loginError != null) {
                    Text(
                        text = loginError ?: "",
                        color = Color.Red,
                        style = TextStyle(fontSize = 14.sp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botón de registro de usuario
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("¿Tienes una cuenta?", color = BlueGrey)
                    TextButton(onClick = { navController.navigate(Screen.Selection.route) }) {
                        Text("REGISTRARME", color = DarkBlue)
                    }
                }
            }
        }
    }
}

