package com.kritan.nityahealth.feature_auth.data.models


data class UserRegister(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
    val address: String,
    val phone: String,
)