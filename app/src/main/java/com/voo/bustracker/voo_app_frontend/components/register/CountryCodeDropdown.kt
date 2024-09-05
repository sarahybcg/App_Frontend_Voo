package com.voo.bustracker.voo_app_frontend.components.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.voo.bustracker.voo_app_frontend.model.entities.CountryCode
import androidx.compose.foundation.layout.*
import androidx.compose.ui.window.Popup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.graphics.Color
import com.voo.bustracker.voo_app_frontend.ui.theme.DarkBlue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCodeDropdown(
    countryCodes: List<CountryCode>,
    selectedCountryCode: CountryCode?,
    onCountryCodeSelected: (CountryCode) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    // Filtro para buscar país por bandera o código
    val filteredCountryCodes = countryCodes.filter {
        it.flag.contains(searchQuery, ignoreCase = true) ||
                it.code.contains(searchQuery)
    }

    // Código de Venezuela por defecto si no se ha seleccionado uno
    val defaultCountryCode = countryCodes.find { it.code == "+58" } ?: countryCodes.first()
    val currentCountryCode = selectedCountryCode ?: defaultCountryCode

    // Establece un ancho fijo para reducir el tamaño del TextField
    val reducedWidth = 100.dp

    // TextField que muestra solo el código del país seleccionado sin borde
    TextField(
        value = currentCountryCode.code,
        onValueChange = {},
        readOnly = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent, // Sin borde inferior
            unfocusedIndicatorColor = Color.Transparent, // Sin borde inferior
            focusedIndicatorColor = Color.Transparent // Sin borde inferior
        ),
        textStyle = TextStyle(
            color = Color(0xFF080092),
            fontWeight = FontWeight.Bold // Establece el texto en negrita
        ),
        modifier = Modifier
            .width(reducedWidth) // Establece un ancho fijo reducido para el campo de código
            .clickable { expanded = true },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.clickable { expanded = !expanded }
            )
        },
        singleLine = true
    )

    // Mostrar el Popup en el centro de la pantalla cuando el campo está expandido
    if (expanded) {
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = { expanded = false },
            properties = PopupProperties(focusable = true)
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFF6F2F2), // Fondo color #F6F2F2
                shadowElevation = 8.dp // Sombra para destacar el menú
            ) {
                Column(
                    modifier = Modifier
                        .width(200.dp) // Ajusta el ancho del menú desplegable
                        .padding(16.dp)
                ) {
                    // Campo de búsqueda dentro del menú
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = { Text("Buscar") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Gray,
                            unfocusedBorderColor = Color.LightGray
                        )
                    )

                    // Elementos del menú mostrando banderas y códigos
                    filteredCountryCodes.forEach { countryCode ->
                        DropdownMenuItem(
                            text = { Text(text = "${countryCode.flag} ${countryCode.code}", color = Color.Gray) },
                            onClick = {
                                onCountryCodeSelected(countryCode)
                                expanded = false
                                searchQuery = "" // Limpiar búsqueda después de la selección
                            }
                        )
                    }
                }
            }
        }
    }
}
