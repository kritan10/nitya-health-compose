package com.kritan.nityahealth.feature_auth.screens.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.kritan.nityahealth.commons.components.MyButton
import com.kritan.nityahealth.feature_auth.utils.AuthFooter
import com.kritan.nityahealth.feature_auth.utils.AuthScreenLayout
import com.kritan.nityahealth.ui.components.MyTextField

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    onSignUpClick: () -> Unit
) {
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var address by remember {
        mutableStateOf("")
    }
    var phone by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }

    AuthScreenLayout(title = "Sign Up", onNavigateUp = onNavigateUp) {
        MyTextField(
            label = "First name",
            value = firstName,
            onValueChange = { value -> firstName = value }
        )
        MyTextField(
            label = "Last name",
            value = lastName,
            onValueChange = { value -> lastName = value }
        )
        MyTextField(
            label = "Address",
            value = address,
            onValueChange = { value -> address = value }
        )
        MyTextField(
            label = "Phone no.",
            value = phone,
            onValueChange = { value -> phone = value }
        )
        MyTextField(
            label = "Email address",
            value = email,
            onValueChange = { value -> email = value }
        )
        MyTextField(
            label = "Password",
            value = password,
            onValueChange = { value -> password = value }
        )
        MyTextField(
            label = "Confirm password",
            value = confirmPassword,
            onValueChange = { value -> confirmPassword = value }
        )
        MyButton(label = "Sign Up", onClick = onSignUpClick)
        AuthFooter(
            text = "Already have an account?", buttonText = "Sign In", onClick = onNavigateToSignIn
        )
    }
}