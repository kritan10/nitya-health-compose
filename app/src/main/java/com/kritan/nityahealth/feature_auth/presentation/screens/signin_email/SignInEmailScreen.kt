package com.kritan.nityahealth.feature_auth.presentation.screens.signin_email

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.kritan.nityahealth.commons.components.MyButton
import com.kritan.nityahealth.feature_auth.presentation.utils.AuthFooter
import com.kritan.nityahealth.feature_auth.presentation.utils.AuthScreenLayout
import com.kritan.nityahealth.ui.components.MyTextField

@Composable
fun SignInEmailScreen(
    onNavigateUp: () -> Unit,
    onSignInClick: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    AuthScreenLayout(title = "Sign In", onNavigateUp = onNavigateUp) {
        MyTextField(
            label = "Email address",
            value = email,
            onValueChange = { value -> email = value })
        MyTextField(
            label = "Password",
            value = password,
            onValueChange = { value -> password = value })
        MyButton(label = "Sign in", onClick = onSignInClick)
        AuthFooter(
            text = "Don’t have account yet?",
            buttonText = "Sign Up",
            onClick = onNavigateToSignUp
        )
    }
}
