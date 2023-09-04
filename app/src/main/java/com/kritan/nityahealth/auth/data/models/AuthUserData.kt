package com.kritan.nityahealth.auth.data.models

sealed class AuthUserData {
    data class UserLogin(
        val email: String = "",
        val password: String = ""
    ) : AuthUserData()

    data class UserRegister(
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val address: String = "",
        val phone: String = "",
    ): AuthUserData()
}