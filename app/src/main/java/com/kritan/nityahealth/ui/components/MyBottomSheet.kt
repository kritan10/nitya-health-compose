package com.kritan.nityahealth.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet(
    isSheetOpen: Boolean,
    title: String,
    sheetState: SheetState,
    closeSheet: () -> Unit,
    content: @Composable () -> Unit
) {
    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = closeSheet,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            Column(
                Modifier
                    .padding(bottom = 8.dp, start = 12.dp, end = 12.dp)
            ) {
                Text(text = title, fontSize = 18.sp)
                Spacer(Modifier.height(24.dp))
                content()
            }
        }
    }
}
