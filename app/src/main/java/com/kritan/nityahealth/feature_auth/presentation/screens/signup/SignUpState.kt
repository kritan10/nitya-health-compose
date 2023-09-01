package com.kritan.nityahealth.feature_auth.presentation.screens.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class SignUpState(
    val isLoading: Boolean = false,

    val currentFirstName: String = "",
    val currentLastName: String = "",
    val currentAddress: String = "",

    val currentPhone: String = "",
    val currentPhoneErrors: MutableList<String> = mutableListOf(),

    val currentEmail: String = "",
    val currentEmailErrors: MutableList<String> = mutableListOf(),

    val currentPassword: String = "",
    val currentPasswordErrors: MutableList<String> = mutableListOf(),

    val currentConfirmPassword: String = "",
    val currentConfirmPasswordErrors: MutableList<String> = mutableListOf(),

    )
