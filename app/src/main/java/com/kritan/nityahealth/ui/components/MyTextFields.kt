package com.kritan.nityahealth.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
    label: String?,
    value: String,
    placeholder: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
    supportingText: String? = null,
    isError: Boolean = false,
    isPassword: Boolean = false,
    isPasswordMasked: Boolean = true,
    isLastField: Boolean = false,
    isSmall: Boolean = false,
    trailingIconAction: () -> Unit = {},
) {
    val containerColor = if (isSystemInDarkTheme()) mWhite.copy(alpha = 0.05F) else mWhite
    val textColor = MaterialTheme.colorScheme.onBackground
    val placeholderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F)
    val errorColor = MaterialTheme.colorScheme.onError
    val supportingTextColor =
        if (isError) Color.Red.copy(alpha = 0.7F) else MaterialTheme.colorScheme.onBackground.copy(
            alpha = 0.25F
        )
    Box {
        Column {
            label?.let {
                Text(
                    label,
                    style = MaterialTheme.typography.labelLarge.copy(color = textColor)
                )
                Spacer(Modifier.height(5.dp))
            }

            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                placeholder = {
                    Text(
                        placeholder ?: label ?: "",
                        color = placeholderColor
                    )
                },
                isError = isError,
                colors = OutlinedTextFieldDefaults.colors(
                    //Text Color
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    errorTextColor = textColor,
                    disabledTextColor = textColor,

                    //Container Color
                    focusedContainerColor = containerColor,
                    errorContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,

                    //Border Color
                    unfocusedBorderColor = Color.Transparent,
                    errorBorderColor = errorColor,

                    //Placeholder Text Color
                    unfocusedPlaceholderColor = placeholderColor,
                    focusedPlaceholderColor = placeholderColor,
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
                    .then(
                        if (isSmall) {
                            Modifier.width(164.dp)
                        } else {
                            Modifier.fillMaxWidth()
                        }
                    )
            )
            supportingText?.let {
                Spacer(Modifier.height(10.dp))
                Text(
                    it,
                    color = supportingTextColor,
                    fontSize = 10.sp
                )
            }
        }
    }
}