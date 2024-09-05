package com.voo.bustracker.voo_app_frontend.components.onboarding

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.voo.bustracker.voo_app_frontend.ui.theme.PurpleBlue
import com.voo.bustracker.voo_app_frontend.ui.theme.White

@Composable
fun OnboardingNavigation(
    currentPage: Int,
    totalPages: Int,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    onStartClick: () -> Unit
) {
    Row {
        if (currentPage > 0) {
            Button(onClick = onPreviousClick,
                colors = ButtonDefaults.buttonColors(
                containerColor = PurpleBlue)) {
                Text(text = "Anterior", color = White)
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        if (currentPage < totalPages - 1) {
            Button(onClick = onNextClick,
                    colors = ButtonDefaults.buttonColors(
                    containerColor = PurpleBlue)) {
                Text(text = "Siguiente", color = White)
            }
        } else {
            Button(onClick = onStartClick,
                    colors = ButtonDefaults.buttonColors(
                    containerColor = PurpleBlue)) {
                Text(text = "Empezar", color = White)
            }
        }
    }
}