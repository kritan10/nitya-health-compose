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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class AppAuth(private val dataStore: DataStore<Preferences>) {
    private val gson: Gson = GsonBuilder().create()
    private val AUTH_KEY = stringPreferencesKey("auth")

    private var _authState = MutableStateFlow(AuthState())
    var authState = _authState.asStateFlow()

    init {
        getAuthDataFromPrefs()
    }

    suspend fun authenticateUser(authState: AuthState) {
        val authJson = gson.toJson(authState)
        dataStore.edit {
            it[AUTH_KEY] = authJson
        }
        getAuthDataFromPrefs()
    }

    suspend fun completeOnboarding() {
        val authState = _authState.value.copy(isOnboard = true)
        val authJson = gson.toJson(authState)
        dataStore.edit {
            it[AUTH_KEY] = authJson
        }
        getAuthDataFromPrefs()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getAuthDataFromPrefs() {
        GlobalScope.launch(Dispatchers.IO) {
            dataStore.data.map {
                gson.fromJson(it[AUTH_KEY], AuthState::class.java) ?: AuthState()
            }.collectLatest { auth ->
                Log.d("Auth", "Updated Auth")
                _authState.emit(auth)
            }
        }
    }
}