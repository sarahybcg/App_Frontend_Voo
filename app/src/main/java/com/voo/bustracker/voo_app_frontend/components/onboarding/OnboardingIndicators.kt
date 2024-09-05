package com.voo.bustracker.voo_app_frontend.components.onboarding

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voo.bustracker.voo_app_frontend.ui.theme.PurpleBlue

@Composable
fun OnboardingIndicators(
    currentPage: Int,
    totalPages: Int
) {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        for (i in 0 until totalPages) {
            if (i == currentPage) {
                Text("●", modifier = Modifier.padding(horizontal = 10.dp),
                    color = PurpleBlue,
                    fontSize = 30.sp)
            } else {
                Text("○",
                    modifier = Modifier.padding(horizontal = 10.dp),
                    color = PurpleBlue,
                    fontSize = 30.sp)
            }
        }
    }
}