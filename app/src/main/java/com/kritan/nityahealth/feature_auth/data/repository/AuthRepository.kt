package com.kritan.nityahealth.feature_auth.data.repository

import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_auth.data.api.AuthApi
import com.kritan.nityahealth.feature_auth.data.models.AuthToken
import com.kritan.nityahealth.feature_auth.data.models.UserLogin
import com.kritan.nityahealth.feature_auth.data.models.UserRegister
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthRepository {
    suspend fun login(userLogin: UserLogin): Flow<Resource<AuthToken>>

    suspend fun register(userRegister: UserRegister): Flow<Resource<AuthToken>>
}