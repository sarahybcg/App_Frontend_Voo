package com.voo.bustracker.voo_app_frontend.components.register


import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.util.*
import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun DatePickerComponent(
    label: String,
    date: Calendar?,
    onDateChange: (Calendar) -> Unit
) {
    val context = LocalContext.current


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

    val displayDate = date?.let { formatDate(it) } ?: label
    val initialDate = date ?: Calendar.getInstance()

    Text(
        text = displayDate,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showDatePicker(initialDate) }
    )
}


private fun formatDate(date: Calendar): String {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(date.time)
}


