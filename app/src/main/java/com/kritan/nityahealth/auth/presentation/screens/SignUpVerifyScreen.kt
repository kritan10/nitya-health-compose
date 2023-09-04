package com.kritan.nityahealth.auth.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kritan.nityahealth.commons.components.MyButton
import com.kritan.nityahealth.commons.components.MyClickableText
import com.kritan.nityahealth.auth.presentation.utils.AuthScreenLayout

@Composable
fun SignUpVerifyScreen(onNavigateUp: () -> Unit, navigateToLocationPicker: () -> Unit) {
    AuthScreenLayout(
        title = "Verification",
        subtitle = "Insert your code here",
        onNavigateUp = onNavigateUp
    ) {
        MyButton(label = "Continue", onClick = navigateToLocationPicker)
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            MyClickableText(label = "Resend Code") {

            }
            MyClickableText(label = "Change Number") {

            }
        }
    }
}