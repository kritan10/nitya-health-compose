package com.kritan.nityahealth.feature_auth.presentation.screens.signup

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kritan.nityahealth.commons.components.MyButton
import com.kritan.nityahealth.feature_auth.data.models.AuthState
import com.kritan.nityahealth.feature_auth.presentation.utils.AuthFooter
import com.kritan.nityahealth.feature_auth.presentation.utils.AuthScreenLayout
import com.kritan.nityahealth.ui.components.MyTextField

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    onSignUpClick: () -> Unit,
    authenticateUser: (AuthState) -> Unit,
) {
    AuthScreenLayout(title = "Sign Up", onNavigateUp = onNavigateUp) {
        MyTextField(
            label = "First name",
            value = viewModel.uiState.currentFirstName,
            onValueChange = viewModel::onFirstNameUpdate
        )
        MyTextField(
            label = "Last name",
            value = viewModel.uiState.currentLastName,
            onValueChange = viewModel::onLastNameUpdate
        )
        MyTextField(
            label = "Address",
            value = viewModel.uiState.currentAddress,
            onValueChange = viewModel::onAddressUpdate
        )
        MyTextField(
            label = "Phone no.",
            value = viewModel.uiState.currentPhone,
            isError = viewModel.uiState.currentPhoneErrors.isNotEmpty(),
            onValueChange = viewModel::onPhoneUpdate,
        )
        MyTextField(
            label = "Email address",
            value = viewModel.uiState.currentEmail,
            isError = viewModel.uiState.currentEmailErrors.isNotEmpty(),
            onValueChange = viewModel::onEmailUpdate
        )
        MyTextField(
            label = "Password",
            value = viewModel.uiState.currentPassword,
            isError = viewModel.uiState.currentPasswordErrors.isNotEmpty(),
            onValueChange = viewModel::onPasswordUpdate
        )
        MyTextField(
            label = "Confirm password",
            value = viewModel.uiState.currentConfirmPassword,
            isError = viewModel.uiState.currentConfirmPasswordErrors.isNotEmpty(),
            onValueChange = viewModel::onConfirmPasswordUpdate
        )
        MyButton(
            label = "Sign Up", onClick = {
                viewModel.registerUser(authenticateUser)
            },
            enabled = viewModel.isRegisterEnabled
        )

        AuthFooter(
            text = "Already have an account?", buttonText = "Sign In", onClick = onNavigateToSignIn
        )
    }
}