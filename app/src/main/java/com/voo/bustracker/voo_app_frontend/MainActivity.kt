package com.voo.bustracker.voo_app_frontend

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.voo.bustracker.voo_app_frontend.navigation.AppNavGraph
import com.voo.bustracker.voo_app_frontend.network.RetrofitClient
import com.voo.bustracker.voo_app_frontend.ui.theme.Voo_app_frontendTheme

class MainActivity : ComponentActivity() {

    private lateinit var locationPermissionLauncher: ActivityResultLauncher<String>
    private var showDialog by mutableStateOf(false)
    private var isLoggedIn by mutableStateOf(false) // Simula el estado de inicio de sesión

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RetrofitClient.initialize(this)

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

                    // Aplicar innerPadding al contenedor del contenido
                    Box(modifier = Modifier.padding(innerPadding)) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            AppNavGraph(navController = navController, isLoggedIn = isLoggedIn)
                        } else {
                            // Manejar versiones de Android más bajas si es necesario
                            // Mostrar una pantalla alternativa o mensaje de error si no se soportan ciertas funciones
                            Text(text = "Esta aplicación requiere una versión de Android superior para funcionar correctamente.")
                        }
                    }

                    // Mostrar el diálogo de permisos si es necesario
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
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
