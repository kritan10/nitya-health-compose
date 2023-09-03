package com.kritan.nityahealth.feature_auth.presentation.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kritan.nityahealth.commons.components.MyButton
import com.kritan.nityahealth.feature_auth.presentation.utils.AuthFooter
import com.kritan.nityahealth.feature_auth.presentation.utils.AuthScreenLayout
import com.kritan.nityahealth.ui.components.MyTextField
import com.kritan.nityahealth.ui.layouts.MyLoadingLayout

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
    onNavigateToSignIn: () -> Unit,
) {
    MyLoadingLayout(loading = viewModel.uiState.isLoading) {
        AuthScreenLayout(title = "Sign Up", onNavigateUp = onNavigateUp) {
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
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
                    supportingText = viewModel.uiState.currentPhoneErrors.firstOrNull(),
                    onValueChange = viewModel::onPhoneUpdate,
                )
                MyTextField(
                    label = "Email address",
                    value = viewModel.uiState.currentEmail,
                    isError = viewModel.uiState.currentEmailErrors.isNotEmpty(),
                    supportingText = viewModel.uiState.currentEmailErrors.firstOrNull(),
                    onValueChange = viewModel::onEmailUpdate
                )
                MyTextField(
                    label = "Password",
                    value = viewModel.uiState.currentPassword,
                    isError = viewModel.uiState.currentPasswordErrors.isNotEmpty(),
                    onValueChange = viewModel::onPasswordUpdate,
                    supportingText = "Passwords must be at least 8 characters and must contain" +
                            "\n\t- at least one lowercase letter" +
                            "\n\t- at least one uppercase letter" +
                            "\n\t- at least on number" +
                            "\n\t- at least on special character"
                )
                MyTextField(
                    label = "Confirm password",
                    value = viewModel.uiState.currentConfirmPassword,
                    isError = viewModel.uiState.currentConfirmPasswordErrors.isNotEmpty(),
                    isLast = true,
                    supportingText = viewModel.uiState.currentConfirmPasswordErrors.firstOrNull(),
                    onValueChange = viewModel::onConfirmPasswordUpdate
                )
                MyButton(
                    label = "Sign Up",
                    onClick = { viewModel.registerUser() },
                    enabled = viewModel.isRegisterEnabled
                )

                AuthFooter(
                    text = "Already have an account?",
                    buttonText = "Sign In",
                    onClick = onNavigateToSignIn
                )
            }
        }
    }
}