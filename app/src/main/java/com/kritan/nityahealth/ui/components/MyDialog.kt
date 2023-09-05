package com.kritan.nityahealth.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MyDialog(
    isDialogOpen: Boolean,
    title: String,
    text: String,
    confirmButtonLabel: String = "Confirm",
    dismissButtonLabel: String = "Cancel",
    onDismissRequest: () -> Unit,
    onConfirmButton: () -> Unit = {},
    onDismissButton: () -> Unit = {},
) {
    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            confirmButton = {
                MyTextButton(
                    label = confirmButtonLabel,
                    textColor = MaterialTheme.colorScheme.primary
                ) {
                    onDismissRequest()
                    onConfirmButton()
                }
            },
            dismissButton = {
                MyTextButton(
                    label = dismissButtonLabel,
                    textColor = MaterialTheme.colorScheme.primary
                ) {
                    onDismissButton()
                }
            },
            title = { Text(title) },
            text = { Text(text) },
            containerColor = MaterialTheme.colorScheme.background,
            iconContentColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            textContentColor = MaterialTheme.colorScheme.onBackground,
        )
    }
}