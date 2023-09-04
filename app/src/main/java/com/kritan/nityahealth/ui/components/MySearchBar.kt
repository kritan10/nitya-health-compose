package com.kritan.nityahealth.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(placeholder: String = "Search") {
    Box {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            singleLine = true,
            placeholder = { Text(placeholder, color = Color.LightGray) },
            textStyle = MaterialTheme.typography.labelLarge.copy(color = Color.Black),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                unfocusedBorderColor = Color.Transparent
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
