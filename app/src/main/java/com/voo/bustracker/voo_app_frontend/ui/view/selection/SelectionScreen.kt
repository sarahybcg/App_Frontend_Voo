package com.voo.bustracker.voo_app_frontend.ui.view.selection

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.voo.bustracker.voo_app_frontend.R
import com.voo.bustracker.voo_app_frontend.navigation.Screen
import com.voo.bustracker.voo_app_frontend.ui.theme.DarkBlue
import com.voo.bustracker.voo_app_frontend.ui.theme.White

@Composable
 fun SelectionScreen(navController: NavController) {
    Scaffold(
        containerColor = White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Usando el padding proporcionado por Scaffold
                .padding(16.dp), // Agregando padding adicional
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ELIGE TU EXPERIENCIA",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBlue
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Passenger Button
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.PassengerOnboarding.route)
                        }
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.passenger),
                        contentDescription = "Passenger Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "PASAJERO",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                }

                // Driver Button
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.DriverOnboarding.route)
                        }
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.driver),
                        contentDescription = "Driver Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "CONDUCTOR",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

