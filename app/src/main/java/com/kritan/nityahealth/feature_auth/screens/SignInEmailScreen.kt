package com.kritan.nityahealth.feature_auth.screens

import androidx.compose.runtime.Composable
import com.kritan.nityahealth.commons.components.MyButton
import com.kritan.nityahealth.ui.components.MyTextField
import com.kritan.nityahealth.feature_auth.utils.AuthFooter
import com.kritan.nityahealth.feature_auth.utils.AuthScreenLayout

@Composable
fun SignInEmailScreen(
    onNavigateUp: () -> Unit,
    onSignInClick: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    AuthScreenLayout(title = "Sign In", onNavigateUp = onNavigateUp) {
        MyTextField(label = "Email address")
        MyTextField(label = "Password")
        MyButton(label = "Sign in", onClick = onSignInClick)
        AuthFooter(
            text = "Don’t have account yet?",
            buttonText = "Sign Up",
            onClick = onNavigateToSignUp
        )
    }
}
