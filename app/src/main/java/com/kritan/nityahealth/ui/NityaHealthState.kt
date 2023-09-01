package com.kritan.nityahealth.ui

import com.kritan.nityahealth.feature_auth.data.models.AuthState

data class NityaHealthState(
    val isLoading: Boolean = false,
    val auth: AuthState = AuthState()
)