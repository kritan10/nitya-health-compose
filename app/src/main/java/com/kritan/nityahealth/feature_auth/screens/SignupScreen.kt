package com.kritan.nityahealth.feature_auth.screens

import androidx.compose.runtime.Composable
import com.kritan.nityahealth.commons.components.MyButton
import com.kritan.nityahealth.commons.components.MyTextField
import com.kritan.nityahealth.feature_auth.utils.AuthFooter
import com.kritan.nityahealth.feature_auth.utils.AuthScreenLayout

@Composable
fun SignUpScreen(
    onNavigateUp: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    onSignUpClick: () -> Unit
) {
    AuthScreenLayout(title = "Sign Up", onNavigateUp = onNavigateUp) {
        MyTextField(label = "First name")
        MyTextField(label = "Last name")
        MyTextField(label = "Address")
        MyTextField(label = "Phone no.")
        MyTextField(label = "Email address")
        MyTextField(label = "Password")
        MyTextField(label = "Confirm password")
        MyButton(label = "Sign Up", onClick = onSignUpClick)
        AuthFooter(
            text = "Already have an account?",
            buttonText = "Sign In",
            onClick = onNavigateToSignIn
        )
    }
}