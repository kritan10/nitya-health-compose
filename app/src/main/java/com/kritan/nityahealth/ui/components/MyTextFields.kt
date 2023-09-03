package com.kritan.nityahealth.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    label: String,
    value: String,
    supportingText: String? = null,
    isError: Boolean = false,
    isLast: Boolean = false,
    onValueChange: (String) -> Unit
) {
    Box() {
        Column(Modifier.fillMaxWidth()) {
            Text(label, style = MaterialTheme.typography.labelLarge.copy(color = Color.Black))
            Spacer(Modifier.height(5.dp))
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                placeholder = { Text(label, color = Color.LightGray) },
                isError = isError,
                textStyle = MaterialTheme.typography.labelLarge.copy(color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(imeAction = if (isLast) ImeAction.Done else ImeAction.Next),
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .fillMaxWidth(),
            )
            Spacer(Modifier.height(10.dp))
            supportingText?.let {
                Text(
                    it,
                    color = if (!isError) Color.Gray else Color.Red,
                    fontSize = 10.sp
                )
            }
        }
    }
}