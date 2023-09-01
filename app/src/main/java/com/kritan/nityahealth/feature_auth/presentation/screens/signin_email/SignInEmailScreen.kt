package com.kritan.nityahealth.feature_auth.presentation.screens.signin_email

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kritan.nityahealth.commons.components.MyButton
import com.kritan.nityahealth.feature_auth.data.models.AuthState
import com.kritan.nityahealth.feature_auth.presentation.utils.AuthFooter
import com.kritan.nityahealth.feature_auth.presentation.utils.AuthScreenLayout
import com.kritan.nityahealth.ui.components.MyTextField

@Composable
fun SignInEmailScreen(
    viewModel: SignInEmailViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
    onSignInClick: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    authenticateUser: (AuthState) -> Unit
) {

    AuthScreenLayout(title = "Sign In", onNavigateUp = onNavigateUp) {
        MyTextField(
            label = "Email address",
            value = viewModel.uiState.currentEmail,
            isError = viewModel.uiState.currentEmailErrors.isNotEmpty(),
            onValueChange = viewModel::onEmailUpdate
        )
        MyTextField(
            label = "Password",
            value = viewModel.uiState.currentPassword,
            onValueChange = viewModel::onPasswordUpdate
        )
        MyButton(label = "Sign in", onClick = { viewModel.loginUser(authenticateUser) })
        AuthFooter(
            text = "Don’t have account yet?",
            buttonText = "Sign Up",
            onClick = onNavigateToSignUp
        )
    }
}
