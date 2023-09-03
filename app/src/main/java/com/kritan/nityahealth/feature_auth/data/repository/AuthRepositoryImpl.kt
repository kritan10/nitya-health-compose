package com.kritan.nityahealth.feature_auth.data.repository

import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_auth.data.api.AuthApi
import com.kritan.nityahealth.feature_auth.data.models.AuthState
import com.kritan.nityahealth.feature_auth.data.models.AuthToken
import com.kritan.nityahealth.feature_auth.data.models.UserLogin
import com.kritan.nityahealth.feature_auth.data.models.UserRegister
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okio.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApi: AuthApi) : AuthRepository {
    override suspend fun login(userLogin: UserLogin): Flow<Resource<AuthState>> {

        return flow {
            emit(Resource.Loading(true))

            val remoteToken: AuthToken? = try {
                val response = authApi.login(
                    MultipartBody.Part.createFormData("email", userLogin.email),
                    MultipartBody.Part.createFormData("password", userLogin.password),
                )
                response.body()?.data
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't log in"))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't log in"))
                null
            }
            remoteToken?.let {
                emit(
                    Resource.Success(
                        AuthState(
                            isAuth = true,
                            token = remoteToken.token,
                            userName = remoteToken.name
                        )
                    )
                )
            }
            emit(Resource.Error("Couldn't log in"))
            emit(Resource.Loading(false))
        }
    }

    override suspend fun register(userRegister: UserRegister): Flow<Resource<AuthState>> {

        return flow {
            emit(Resource.Loading(true))

            val remoteToken: AuthToken? = try {
                val response = authApi.register(
                    MultipartBody.Part.createFormData("name", userRegister.name),
                    MultipartBody.Part.createFormData("email", userRegister.email),
                    MultipartBody.Part.createFormData("address", userRegister.address),
                    MultipartBody.Part.createFormData("phone", userRegister.phone),
                    MultipartBody.Part.createFormData("password", userRegister.password),
                    MultipartBody.Part.createFormData("c_password", userRegister.confirmPassword),
                )
                response.body()?.data
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }
            remoteToken?.let {
                emit(
                    Resource.Success(
                        AuthState(
                            isAuth = true,
                            token = remoteToken.token,
                            userName = remoteToken.name
                        )
                    )
                )
            }
            emit(Resource.Loading(false))
        }
    }
}