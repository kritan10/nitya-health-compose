package com.kritan.nityahealth.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritan.nityahealth.auth.AppAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NityaHealthViewModel @Inject constructor(private val appAuth: AppAuth) :
    ViewModel() {

    var mainUiState by mutableStateOf(NityaHealthState())
        private set

    init {
        getAuthState()
    }

    private fun getAuthState() {
        viewModelScope.launch {
            appAuth.authState.collectLatest {
                mainUiState = mainUiState.copy(
                    auth = it,
                    isLoading = false
                )
            }
        }
    }
}