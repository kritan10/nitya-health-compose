package com.kritan.nityahealth.auth

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kritan.nityahealth.auth.data.models.AuthState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class AppAuth(private val dataStore: DataStore<Preferences>) {
    private val gson: Gson = GsonBuilder().create()
    private val authKey = stringPreferencesKey("auth")

    private var _authState = MutableStateFlow(AuthState())
    var authState = _authState.asStateFlow()

    init {
        getAuthDataFromPrefs()
        Log.d("Auth", "Init Block")
    }

    suspend fun logOutUser() {
        val authJson = gson.toJson(AuthState(isOnboard = true))
        dataStore.edit {
            it[authKey] = authJson
        }
    }

    suspend fun authenticateUser(authState: AuthState) {
        val authJson = gson.toJson(authState)
        dataStore.edit {
            it[authKey] = authJson
        }
        Log.d("Auth", "Auth User Block")
    }

    suspend fun completeOnboarding() {
        val authState = _authState.value.copy(isOnboard = true)
        val authJson = gson.toJson(authState)
        dataStore.edit {
            it[authKey] = authJson
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getAuthDataFromPrefs() {
        GlobalScope.launch(Dispatchers.IO) {
            dataStore.data.map {
                gson.fromJson(it[authKey], AuthState::class.java) ?: AuthState()
            }.distinctUntilChanged().collect { auth ->
                Log.d("Auth", "Updated Auth")
                _authState.emit(auth)
            }
        }
    }
}