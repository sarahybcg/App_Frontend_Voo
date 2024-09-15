package com.voo.bustracker.voo_app_frontend.components.login

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textInput(
    inputType: InputType,
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions
) {
    // Estado para mostrar u ocultar la contraseña
    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester ?: FocusRequester())
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .focusable(),
        leadingIcon = { Icon(imageVector = inputType.icon, contentDescription = null) },
        label = { Text(text = inputType.label) },
        shape = Shapes().small,
//        colors = TextFieldDefaults.textFieldColors(
//            containerColor = Color.White,
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent,
//            disabledIndicatorColor = Color.Transparent,
//        ),
        singleLine = true,
        keyboardOptions = inputType.keyboardOptions,
        visualTransformation = if (inputType is InputType.Password && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardActions = keyboardActions,
        trailingIcon = {
            if (inputType is InputType.Password) {
                IconButton(onClick = { setPasswordVisible(!passwordVisible) }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            }
        }
    )
}