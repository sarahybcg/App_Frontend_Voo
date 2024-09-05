package com.voo.bustracker.voo_app_frontend

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import com.voo.bustracker.voo_app_frontend.ui.theme.Voo_app_frontendTheme
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.voo.bustracker.voo_app_frontend.ui.theme.Voo_app_frontendTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.voo.bustracker.voo_app_frontend.navigation.AppNavGraph
import android.Manifest
import android.content.Intent
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import android.provider.Settings


class MainActivity : ComponentActivity() {

    private lateinit var locationPermissionLauncher: ActivityResultLauncher<String>
    private var showDialog by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize permission launcher
        locationPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Permission granted, continue
            } else {
                // Permission denied, show dialog if not permanently denied
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    showDialog = true
                }
            }
        }

        // Check if the permission is already granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        setContent {
            Voo_app_frontendTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    AppNavGraph(navController = navController)

                    // Show dialog if needed
                    if (showDialog) {
                        LocationPermissionDialog(onDismiss = { showDialog = false })
                    }
                }
            }
        }
    }
}

@Composable
fun LocationPermissionDialog(onDismiss: () -> Unit) {
    // Correct use of LocalContext.current inside a composable function
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Permiso de Ubicación Requerido") },
        text = {
            Text("Para utilizar la funcionalidad de ubicación, por favor concede acceso a la ubicación. Puedes cambiar la configuración en la app si eliges 'Nunca'.")
        },
        confirmButton = {
            Button(
                onClick = {
                    onDismiss()
                    // Open app settings
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }
                    context.startActivity(intent)
                }
            ) {
                Text("Ir a Configuración")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Cancelar")
            }
        }
    )
}