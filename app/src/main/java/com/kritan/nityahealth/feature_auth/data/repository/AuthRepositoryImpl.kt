package com.kritan.nityahealth.feature_auth.data.repository

import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_auth.data.api.AuthApi
import com.kritan.nityahealth.feature_auth.data.models.AuthToken
import com.kritan.nityahealth.feature_auth.data.models.UserLogin
import com.kritan.nityahealth.feature_auth.data.models.UserRegister
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApi: AuthApi) : AuthRepository {
    override suspend fun login(userLoginData: UserLogin): Flow<Resource<AuthToken>> {
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email", userLoginData.email)
            .addFormDataPart("password", userLoginData.password)
            .build()

        return flow {
            emit(Resource.Loading(true))

            val remoteToken: AuthToken? = try {
                val response = authApi.login(requestBody).execute()
                response.body()?.data
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteToken?.let {
                emit(Resource.Success(it))
            }

            emit(Resource.Loading(false))
        }

    }

    override suspend fun register(userRegisterData: UserRegister): Flow<Resource<AuthToken>> {
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("name", userRegisterData.name)
            .addFormDataPart("email", userRegisterData.email)
            .addFormDataPart("password", userRegisterData.password)
            .addFormDataPart("c_password", userRegisterData.confirmPassword)
            .addFormDataPart("address", userRegisterData.address)
            .addFormDataPart("phone", userRegisterData.phone)
            .build()

        return flow {
            emit(Resource.Loading(true))

            val remoteToken: AuthToken? = try {
                val response = authApi.login(requestBody).execute()
                response.body()?.data
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteToken?.let {
                emit(Resource.Success(it))
            }

            emit(Resource.Loading(false))
        }
    }
}