package com.kritan.nityahealth.auth.presentation.screens.signin

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.kritan.nityahealth.R
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
    private val facebookRepository: FacebookRepository,
    application: Application,
) : ViewModel() {

    val isUserBoarded = appAuth.authState.value.isOnboard
    private var isLoading = false


    init {
        registerFacebookLogin()
    }

    fun boardInUser() {
        viewModelScope.launch {
            appAuth.boardInUser()
        }
    }

    // Facebook Login : Start //
    fun getFacebookLoginContract(): LoginManager.FacebookLoginActivityResultContract {
        val loginManager = LoginManager.getInstance()
        return loginManager.FacebookLoginActivityResultContract(callbackManager)
    }

    private fun registerFacebookLogin() {
        val loginManager = LoginManager.getInstance()
        val tag = "FACEBOOK LOGIN"
        val callback = object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                isLoading = true
                Log.d(tag, result.accessToken.token)
                runBlocking {
                    val name =
                        async { facebookRepository.getUserName(result.accessToken.token) }.await()
                    appAuth.authenticateUser(
                        AuthState(
                            isAuth = true,
                            isOnboard = true,
                            userName = name,
                            token = "FB TOKEN",
                            authSource = AuthSource.Facebook
                        )
                    )
                }
                isLoading = false
            }

            override fun onCancel() {
                Log.d(tag, "onCancel Block")
            }

            override fun onError(error: FacebookException) {
                Log.d(tag, error.message.toString())
                error.printStackTrace()
            }
        }
        loginManager.registerCallback(callbackManager, callback)
    }
    // Facebook Login : End //


    // Google Login : Start //
    private val oneTapClient = Identity.getSignInClient(application)

    private val signInRequest = BeginSignInRequest.builder()
        .setPasswordRequestOptions(
            BeginSignInRequest.PasswordRequestOptions.builder()
                .setSupported(true)
                .build()
        )
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                // Your server's client ID, not your Android client ID.
                .setServerClientId(application.getString(R.string.default_web_client_id))
                // Only show accounts previously used to sign in.
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()

    fun onGoogleSignInActivityLauncherResult(
        activityResult: ActivityResult,
    ) {
        try {
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val credentials = oneTapClient.getSignInCredentialFromIntent(activityResult.data)
                val authenticatedAuthState = AuthState(
                    isAuth = true,
                    isOnboard = true,
                    userName = credentials.displayName,
                    token = "GOOGLE TOKEN",
                    authSource = AuthSource.Google
                )
                appAuth.authenticateUser(authenticatedAuthState)
            } else {
                Log.e("CANCELLED", "else")
            }
        } catch (e: Exception) {
            Log.e("TAG1", "${e.message}")
        }
    }

    fun signInWithGoogle(
        activityLauncher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>
    ) {
        oneTapClient.beginSignIn(signInRequest).addOnSuccessListener { result ->
            try {
                activityLauncher.launch(
                    IntentSenderRequest
                        .Builder(result.pendingIntent.intentSender)
                        .build()
                )
            } catch (e: Exception) {
                Log.e("TAG2", "${e.message}")
            }
        }
            .addOnFailureListener {
                it.printStackTrace()
                Log.e("TAG3", "${it.message}")
            }
    }
    // Google Login : End //
}