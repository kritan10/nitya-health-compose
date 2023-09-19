package com.kritan.nityahealth.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kritan.nityahealth.ui.theme.mWhite


@Composable
fun MyTextField(
    label: String,
    value: String,
    placeholder: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
    supportingText: String? = null,
    isError: Boolean = false,
    isPassword: Boolean = false,
    isPasswordMasked: Boolean = true,
    isLastField: Boolean = false,
    trailingIconAction: () -> Unit = {},
) {
    val containerColor = if (isSystemInDarkTheme()) mWhite.copy(alpha = 0.05F) else mWhite
    Box {
        Column(Modifier.fillMaxWidth()) {
            Text(
                label,
                style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onBackground)
            )
            Spacer(Modifier.height(5.dp))
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                placeholder = { Text(placeholder ?: label) },
                isError = isError,
                colors = OutlinedTextFieldDefaults.colors(
                    //Text Color
                    focusedTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground,

                    //Container Color
                    focusedContainerColor = containerColor,
                    errorContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = Color.White,

                    //Border Color
                    unfocusedBorderColor = Color.Transparent,

                    //Placeholder Text Color
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25F),
                    focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25F),
                ),
                trailingIcon = {
                    if (isPassword) {
                        IconButton(onClick = trailingIconAction) {
                            Icon(
                                if (!isPasswordMasked) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                "",
                                tint = Color.Gray
                            )
                        }
                    }
                },
                visualTransformation = if (isPassword && isPasswordMasked) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    imeAction = if (isLastField) ImeAction.Done else ImeAction.Next,
                    keyboardType = keyboardType
                ),
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