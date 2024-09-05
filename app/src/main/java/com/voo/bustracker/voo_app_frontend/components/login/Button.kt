package com.voo.bustracker.voo_app_frontend.components.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.voo.bustracker.voo_app_frontend.ui.theme.DarkBlue
import com.voo.bustracker.voo_app_frontend.ui.theme.PurpleBlue
import com.voo.bustracker.voo_app_frontend.ui.theme.White

@Composable
fun Button( text: String,
            onClick: () -> Unit,
            enabled: Boolean = true,
            modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) Color.Blue else Color.Gray
        )
    ) {
        Text(text, color = White)
    }
}