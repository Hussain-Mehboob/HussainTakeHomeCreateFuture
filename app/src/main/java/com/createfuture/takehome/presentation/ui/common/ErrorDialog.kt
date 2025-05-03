package com.createfuture.takehome.presentation.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ErrorDialog(errorMessage: String, onDismiss: () -> Unit) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when clicked outside or when the button is pressed
                showDialog = false
                onDismiss()
            },
            title = {
                Text(
                    text = "Error",
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            text = {
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        onDismiss()
                    }
                ) {
                    Text(
                        text = "OK",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        )
    }
}