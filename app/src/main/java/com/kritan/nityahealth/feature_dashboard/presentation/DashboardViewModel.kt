package com.kritan.nityahealth.feature_dashboard.presentation

import androidx.lifecycle.ViewModel
import com.kritan.nityahealth.auth.AppAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val appAuth: AppAuth) : ViewModel() {
    val userName = appAuth.authState.value.userName ?: "Falano"
}