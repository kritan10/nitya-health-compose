package com.kritan.nityahealth.auth

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.facebook.login.LoginManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kritan.nityahealth.auth.data.models.AuthSource
import com.kritan.nityahealth.auth.data.models.AuthState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AppAuth(private val dataStore: DataStore<Preferences>) {
    private val gson: Gson = GsonBuilder().create()
    private val authKey = stringPreferencesKey("auth")

    private val TAG = "AppAuth"

    private var _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()

    init {
        initAuthFromPreferences()
        Log.d(TAG, "Init Complete")
    }


    fun authenticateUser(authState: AuthState) {
        modifyAuthState(authState)
        Log.d(TAG, "Authenticated User")
    }

    fun logOutUser() {
        when (_authState.value.authSource) {
            AuthSource.Facebook -> {
                LoginManager.getInstance().logOut()
            }

            else -> Unit
        }
        removeUserAuthentication()
        Log.d(TAG, "Logged Out User")
    }

    fun boardInUser() {
        modifyAuthState(_authState.value.copy(isOnboard = true))
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun initAuthFromPreferences() {
        Log.d(TAG, "Init Auth")
        GlobalScope.launch(Dispatchers.IO) {
            val initialAuth = dataStore.data.map {
                gson.fromJson(it[authKey], AuthState::class.java) ?: AuthState()
            }.first()
            Log.d(TAG, "Initial Value: $initialAuth")
            _authState.update { _ -> initialAuth }
            delay(200)
            this.cancel()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun modifyAuthState(authState: AuthState) {
        _authState.update { _ -> authState }
        GlobalScope.launch(Dispatchers.IO) {
            val authJson = gson.toJson(authState)
            dataStore.edit {
                it[authKey] = authJson
            }
            delay(200)
            this.cancel()
        }
        Log.d(TAG, "Auth Modified $authState")

    }

    private fun removeUserAuthentication() {
        val newAuthState = _authState.value.copy(
            isAuth = false,
            userName = null,
            token = null,
            authSource = AuthSource.None
        )
        modifyAuthState(newAuthState)
    }
}