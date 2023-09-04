package com.kritan.nityahealth.auth.presentation.screens.signin_email

data class SignInEmailState(
    val isLoading:Boolean = false,

    val currentEmail: String = "",
    val currentEmailErrors: MutableList<String> = mutableListOf(),

    val currentPassword: String = "",
)