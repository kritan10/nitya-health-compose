package com.kritan.nityahealth.feature_dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritan.nityahealth.auth.AppAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val appAuth: AppAuth) : ViewModel() {
    lateinit var userName: String

    init {
        viewModelScope.launch {
            appAuth.authState.map {
                it.userName
            }.stateIn(viewModelScope, SharingStarted.Lazily, "Falano")
                .collectLatest {
                    userName = it ?: ""
                }
        }
    }
}