package com.voo.bustracker.voo_app_frontend.ui.view.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.voo.bustracker.voo_app_frontend.components.onboarding.OnboardingIndicators
import com.voo.bustracker.voo_app_frontend.components.onboarding.OnboardingNavigation
import com.voo.bustracker.voo_app_frontend.navigation.Screen
import com.voo.bustracker.voo_app_frontend.ui.theme.*
import com.voo.bustracker.voo_app_frontend.viewmodel.OnboardingViewModel

@Composable
fun OnboardingScreen(
    navController: NavController,
    destination: String,
    isPassenger: Boolean,
    viewModel: OnboardingViewModel = viewModel(),
    ) {
    val pages = viewModel.getCurrentPages(isPassenger)

    Scaffold(
        containerColor = purple
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                Image(
                    painter = painterResource(id = pages[viewModel.currentPage].imageRes),
                    contentDescription = "Onboarding Image",
                    modifier = Modifier
                        //.padding(vertical = 50.dp)
                    // modifier = Modifier
                     .fillMaxWidth()
                    .weight(0.09f)
                )

                Text(
                    text = pages[viewModel.currentPage].text,
                    color = DarkBlue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                OnboardingIndicators(
                    currentPage = viewModel.currentPage,
                    totalPages = pages.size
                )
                OnboardingNavigation(
                    currentPage = viewModel.currentPage,
                    totalPages = pages.size,
                    onPreviousClick = { viewModel.previousPage() },
                    onNextClick = { viewModel.nextPage() },
                    onStartClick = {
                        navController.navigate(destination) {
                            // Limpia la pila de navegación para que el usuario no pueda volver al onboarding
                            popUpTo(Screen.PassengerOnboarding.route) { inclusive = true }
                            popUpTo(Screen.DriverOnboarding.route) { inclusive = true }
                        }
                        viewModel.reset()
                    }
                )
            }
        }
    }
}