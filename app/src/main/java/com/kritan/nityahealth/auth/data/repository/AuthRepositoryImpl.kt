package com.kritan.nityahealth.auth.data.repository

import com.kritan.nityahealth.auth.data.api.AuthApi
import com.kritan.nityahealth.auth.data.models.AuthState
import com.kritan.nityahealth.auth.data.models.AuthToken
import com.kritan.nityahealth.auth.data.models.AuthUserData
import com.kritan.nityahealth.base.api.MyApi
import com.kritan.nityahealth.base.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApi: AuthApi) : AuthRepository {
    override suspend fun login(userLogin: AuthUserData.UserLogin): Flow<Resource<AuthState>> {
        return authenticate(userLogin)
    }

    override suspend fun register(userRegister: AuthUserData.UserRegister): Flow<Resource<AuthState>> {
        return authenticate(userRegister)
    }

    private suspend fun authenticate(userData: AuthUserData): Flow<Resource<AuthState>> {
        var remoteToken: AuthToken? = null
        return flow {
            emit(Resource.Loading(true))

            when (userData) {
                is AuthUserData.UserRegister -> {
                    remoteToken = MyApi.fetchFromRemote {
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
                    remoteToken = MyApi.fetchFromRemote {
                        authApi.login(
                            MultipartBody.Part.createFormData("email", userData.email),
                            MultipartBody.Part.createFormData("password", userData.password),
                        )
                    }
                }
            }

            if (remoteToken != null) {
                emit(
                    Resource.Success(
                        AuthState(
                            isAuth = true,
                            token = remoteToken!!.token,
                            userName = remoteToken!!.name
                        )
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