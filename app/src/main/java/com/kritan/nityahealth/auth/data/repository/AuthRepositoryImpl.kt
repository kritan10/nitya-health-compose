package com.kritan.nityahealth.auth.data.repository

import android.app.Activity
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.kritan.nityahealth.auth.data.api.AuthApi
import com.kritan.nityahealth.auth.data.models.AuthSource
import com.kritan.nityahealth.auth.data.models.AuthState
import com.kritan.nityahealth.auth.data.models.AuthToken
import com.kritan.nityahealth.auth.data.models.AuthUserData
import com.kritan.nityahealth.base.api.ApiResponse
import com.kritan.nityahealth.base.api.MyApi
import com.kritan.nityahealth.base.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApi: AuthApi) : AuthRepository {
    override suspend fun loginWithEmail(userLogin: AuthUserData.UserLogin): Flow<Resource<AuthState>> {
        return authenticate(userLogin)
    }

    override suspend fun registerWithEmail(userRegister: AuthUserData.UserRegister): Flow<Resource<AuthState>> {
        return authenticate(userRegister)
    }

    override suspend fun loginWithFacebook(activity: Activity): Resource<AuthState> {
        var authState = AuthState()
        val loginManager = LoginManager.getInstance()
        val callbackManager = CallbackManager.Factory.create()
        val facebookCallback = object : FacebookCallback<LoginResult> {
            val TAG = "FB AUTH"
            override fun onSuccess(result: LoginResult) {
                authState = AuthState(
                    isAuth = true,
                    isOnboard = true,
                    token = "fb token",
                    userName = result.accessToken.userId,
                    authSource = AuthSource.Facebook
                )

                Log.d(TAG, result.accessToken.token)
                Log.d(TAG, result.authenticationToken.toString())
            }

            override fun onCancel() {
                Log.d(TAG, "onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "onError")
            }
        }

        loginManager.registerCallback(callbackManager, facebookCallback)
        loginManager.logIn(activity, listOf("email"))

        return if (authState.isAuth) {
            Resource.Success(data = authState, "Facebook Login successful")
        } else {
            Resource.Error("Error logging in")
        }

    }

    private suspend fun authenticate(userData: AuthUserData): Flow<Resource<AuthState>> {
        var authApiResponse: ApiResponse<AuthToken>? = null
        return flow {
            emit(Resource.Loading(true))

            when (userData) {
                is AuthUserData.UserRegister -> {
                    authApiResponse = MyApi.fetchFromRemote {
                        authApi.register(
                            MultipartBody.Part.createFormData("name", userData.name),
                            MultipartBody.Part.createFormData("email", userData.email),
                            MultipartBody.Part.createFormData("address", userData.address),
                            MultipartBody.Part.createFormData("phone", userData.phone),
                            MultipartBody.Part.createFormData("password", userData.password),
                            MultipartBody.Part.createFormData(
                                "c_password",
                                userData.confirmPassword
                            ),
                        )
                    }
                }

                is AuthUserData.UserLogin -> {
                    authApiResponse = MyApi.fetchFromRemote {
                        authApi.login(
                            MultipartBody.Part.createFormData("email", userData.email),
                            MultipartBody.Part.createFormData("password", userData.password),
                        )
                    }
                }
            }

            if (authApiResponse != null) {
                emit(
                    Resource.Success(
                        data = AuthState(
                            isAuth = true,
                            isOnboard = true,
                            token = authApiResponse!!.data!!.token,
                            userName = authApiResponse!!.data!!.name,
                            authSource = AuthSource.Email
                        ),
                        message = "Log in successful"
                    )
                )
                delay(1000)
            } else {
                emit(Resource.Error("Couldn't log in"))
            }
            emit(Resource.Loading(false))
        }
    }
}