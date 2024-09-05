package com.voo.bustracker.voo_app_frontend.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun ProfileIcon(
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(40.dp) // Tamaño del marco
            .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.small) // Marco con forma redondeada
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp) // Tamaño del ícono
                .align(Alignment.Center), // Centra el ícono en el marco
            tint = MaterialTheme.colorScheme.onSurface // Color del ícono
        )
    }
}

