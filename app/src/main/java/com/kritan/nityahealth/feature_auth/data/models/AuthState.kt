package com.kritan.nityahealth.feature_auth.data.models

data class AuthState(
    val isAuth: Boolean = false,
    val userName: String? = null,
    val token: String? = null,
)
