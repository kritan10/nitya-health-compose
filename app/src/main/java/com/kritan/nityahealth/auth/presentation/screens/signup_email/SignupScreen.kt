package com.kritan.nityahealth.auth.presentation.screens.signup_email

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kritan.nityahealth.auth.presentation.utils.AuthFooter
import com.kritan.nityahealth.auth.presentation.utils.AuthScreenLayout
import com.kritan.nityahealth.commons.components.MyButton
import com.kritan.nityahealth.ui.components.MyTextField
import com.kritan.nityahealth.ui.layouts.MyLoadingLayout

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
    onNavigateToSignIn: () -> Unit,
) {
    var isPasswordMasked by remember {
        mutableStateOf(true)
    }

    fun togglePasswordMask() {
        isPasswordMasked = !isPasswordMasked
    }

    MyLoadingLayout(loading = viewModel.uiState.isLoading) {
        AuthScreenLayout(title = "Sign Up", onNavigateUp = onNavigateUp) {
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                MyTextField(
                    label = "First name",
                    value = viewModel.uiState.currentFirstName,
                    onValueChange = viewModel::onFirstNameUpdate,
                )
                MyTextField(
                    label = "Last name",
                    value = viewModel.uiState.currentLastName,
                    onValueChange = viewModel::onLastNameUpdate,
                )
                MyTextField(
                    label = "Address",
                    value = viewModel.uiState.currentAddress,
                    onValueChange = viewModel::onAddressUpdate,
                )
                MyTextField(
                    label = "Phone no.",
                    value = viewModel.uiState.currentPhone,
                    supportingText = viewModel.uiState.currentPhoneErrors.firstOrNull(),
                    isError = viewModel.uiState.currentPhoneErrors.isNotEmpty(),
                    onValueChange = viewModel::onPhoneUpdate,
                )
                MyTextField(
                    label = "Email address",
                    value = viewModel.uiState.currentEmail,
                    supportingText = viewModel.uiState.currentEmailErrors.firstOrNull(),
                    isError = viewModel.uiState.currentEmailErrors.isNotEmpty(),
                    onValueChange = viewModel::onEmailUpdate,
                )
                MyTextField(
                    label = "Password",
                    value = viewModel.uiState.currentPassword,
                    supportingText = "Passwords must be at least 8 characters and must contain" +
                            "\n\t- at least one lowercase letter" +
                            "\n\t- at least one uppercase letter" +
                            "\n\t- at least on number" +
                            "\n\t- at least on special character",
                    isError = viewModel.uiState.currentPasswordErrors.isNotEmpty(),
                    onValueChange = viewModel::onPasswordUpdate,
                    isPassword = true,
                    isPasswordMasked = isPasswordMasked,
                    trailingIconAction = ::togglePasswordMask,
                )
                MyTextField(
                    label = "Confirm password",
                    value = viewModel.uiState.currentConfirmPassword,
                    supportingText = viewModel.uiState.currentConfirmPasswordErrors.firstOrNull(),
                    isError = viewModel.uiState.currentConfirmPasswordErrors.isNotEmpty(),
                    isLastField = true,
                    onValueChange = viewModel::onConfirmPasswordUpdate,
                    isPassword = true,
                    isPasswordMasked = isPasswordMasked,
                    trailingIconAction = ::togglePasswordMask,
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