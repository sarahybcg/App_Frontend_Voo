package com.voo.bustracker.voo_app_frontend.components.register

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import com.voo.bustracker.voo_app_frontend.ui.theme.AzulClaro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isSingleLine: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    modifier: Modifier = Modifier,
    width: Dp? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = modifier
            .then(if (width != null) Modifier.width(width) else Modifier), // Aplica el ancho si se proporciona
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        singleLine = isSingleLine, // Añadir control para una sola línea
        shape = Shapes().small,
//        colors = TextFieldDefaults.textFieldColors(
//            containerColor = Color.Transparent,
//            focusedIndicatorColor = Color.Blue, // Cambia a tu color deseado
//            unfocusedIndicatorColor = Color.Transparent,
//            disabledIndicatorColor = Color.Blue, // Cambia a tu color deseado
//            errorIndicatorColor = Color.Red
//        )
    )

    // Mostrar mensaje de error si existe
    if (isError && errorMessage != null) {
        Text(
            text = errorMessage,
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall
        )
    }
}