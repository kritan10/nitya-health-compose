package com.kritan.nityahealth.auth.presentation.screens.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.kritan.nityahealth.auth.AppAuth
import com.kritan.nityahealth.auth.data.models.AuthSource
import com.kritan.nityahealth.auth.data.models.AuthState
import com.kritan.nityahealth.auth.data.repository.FacebookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val appAuth: AppAuth,
    private val callbackManager: CallbackManager,
    private val facebookRepository: FacebookRepository
) : ViewModel() {

    val isUserBoarded = appAuth.authState.value.isOnboard
    private var isLoading = false
    private val loginManager = LoginManager.getInstance()

    init {
        registerFacebookLogin()
    }

    fun boardInUser() {
        viewModelScope.launch {
            appAuth.boardInUser()
        }
    }

    fun getFacebookLoginContract(): LoginManager.FacebookLoginActivityResultContract {
        return loginManager.FacebookLoginActivityResultContract(callbackManager)
    }

    private fun registerFacebookLogin() {
        val TAG = "FACEBOOK LOGIN"
        val callback = object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                isLoading = true
                Log.d(TAG, result.accessToken.token)
                runBlocking {
                    val name =
                        async { facebookRepository.getUserName(result.accessToken.token) }.await()
                    appAuth.authenticateUser(
                        AuthState(
                            isAuth = true,
                            isOnboard = true,
                            userName = name,
                            token = "FBTOKEN",
                            authSource = AuthSource.Facebook
                        )
                    )
                }
                isLoading = false
            }

            override fun onCancel() {
                Log.d(TAG, "onCancel Block")
                // App code
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, error.message.toString())
                error.printStackTrace()
                // App code
            }
        }
        loginManager.registerCallback(callbackManager, callback)
    }
}