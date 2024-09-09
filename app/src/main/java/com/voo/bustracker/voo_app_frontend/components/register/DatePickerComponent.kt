package com.voo.bustracker.voo_app_frontend.components.register


import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DatePickerComponent(
    label: String,
    date: Calendar?,
    onDateChange: (Calendar) -> Unit
) {
    val context = LocalContext.current

    // Function to show DatePickerDialog
    fun showDatePicker(initialDate: Calendar) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate.set(year, month, dayOfMonth)
                onDateChange(newDate)
            },
            initialDate.get(Calendar.YEAR),
            initialDate.get(Calendar.MONTH),
            initialDate.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    // Format the date if available, otherwise show the label
    val displayDate = date?.let { formatDate(it) } ?: label

    // Set initial date to current date if null
    val initialDate = date ?: Calendar.getInstance()

    // Display the date in a Text component
    Text(
        text = displayDate,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showDatePicker(initialDate) }
    )
}

// Function to format the date from Calendar to String
private fun formatDate(date: Calendar): String {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(date.time)
}



