package com.kritan.nityahealth.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.ui.theme.mWhite

@Composable
fun MySearchBar(placeholder: String = "Search") {
    val containerColor = if (isSystemInDarkTheme()) mWhite.copy(alpha = 0.05F) else mWhite

    Box {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            singleLine = true,
            placeholder = { Text(placeholder, color = Color.LightGray) },
            textStyle = MaterialTheme.typography.labelLarge.copy(color = Color.Black),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                unfocusedBorderColor = Color.Transparent,
            ),
            trailingIcon = {
                Icon(
                    Icons.Default.Search,
                    "",
                    Modifier.width(16.dp),
                    Color.LightGray
                )
            },
            modifier = Modifier
                .shadow(
                    elevation = 10.dp,
                    spotColor = Color(0x60000000),
                    ambientColor = Color(0x60000000)
                )
                .fillMaxWidth()
        )
    }
}
