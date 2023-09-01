package com.kritan.nityahealth.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kritan.nityahealth.feature_auth.data.models.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NityaHealthViewModel @Inject constructor(private val dataStore: DataStore<Preferences>) :
    ViewModel() {

    var mainUiState by mutableStateOf(NityaHealthState())
        private set

    private val gson: Gson = GsonBuilder().create()

    private val AUTH_KEY = stringPreferencesKey("auth")

    init {
        getAuthDataFromPrefs()
    }

    fun authenticateUser(authState: AuthState) {
        viewModelScope.launch {
            mainUiState = mainUiState.copy(
                isLoading = true
            )
            val authJson = gson.toJson(authState)
            dataStore.edit {
                it[AUTH_KEY] = authJson
            }
            delay(1000)
            mainUiState = mainUiState.copy(
                auth = authState,
                isLoading = false
            )
        }
    }

    private fun getAuthDataFromPrefs() {
        viewModelScope.launch {
            mainUiState = mainUiState.copy(
                isLoading = true
            )
            val authFlow = dataStore.data.map {
                it[AUTH_KEY] ?: ""
            }
            authFlow.collect {
                val authState: AuthState? = gson.fromJson(it, AuthState::class.java)
                authState?.let {
                    mainUiState = mainUiState.copy(
                        auth = authState
                    )
                }
                mainUiState = mainUiState.copy(
                    isLoading = false
                )
            }
        }
    }
}