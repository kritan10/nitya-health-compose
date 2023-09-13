package com.kritan.nityahealth.auth.data.models

data class AuthState(
    val isAuth: Boolean = false,
    val isOnboard: Boolean = false,
    val userName: String? = null,
    val token: String? = null,
    val authSource: AuthSource = AuthSource.None
)
