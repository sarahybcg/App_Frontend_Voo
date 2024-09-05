package com.voo.bustracker.voo_app_frontend.components.register

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import coil.compose.rememberImagePainter
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.ui.layout.ContentScale
import coil.request.ImageRequest

@Composable
fun ImageUploadComponent(
    onImageSelected: (Uri?) -> Unit,
    buttonText: String = "Seleccionar imagen"
) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Launcher to select image from gallery
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
        onImageSelected(uri)
    }

    val permissionCheckResult = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)

    // Handle permissions and image selection
    if (permissionCheckResult != PackageManager.PERMISSION_GRANTED) {
        // Request permission
        val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                launcher.launch("image/*")
            } else {
                // Handle permission denied scenario (e.g., show a message)
            }
        }

        // Button to request permission
        Button(
            onClick = {
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Permitir acceso a almacenamiento")
        }
    } else {
        // Button to select image
        Button(
            onClick = {
                launcher.launch("image/*")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = buttonText)
        }
    }

    // Display the selected image
    imageUri?.let {
        val painter = rememberImagePainter(
            ImageRequest.Builder(context)
                .data(it)
                .build()
        )
        Image(
            painter = painter,
            contentDescription = "Imagen seleccionada",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(200.dp),  // Adjust height as needed
            contentScale = ContentScale.Crop
        )
    }
}