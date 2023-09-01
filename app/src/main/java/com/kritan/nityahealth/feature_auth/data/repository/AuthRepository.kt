package com.kritan.nityahealth.feature_auth.data.repository

import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_auth.data.models.AuthState
import com.kritan.nityahealth.feature_auth.data.models.UserLogin
import com.kritan.nityahealth.feature_auth.data.models.UserRegister
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(userLogin: UserLogin): Flow<Resource<AuthState>>

    suspend fun register(userRegister: UserRegister): Flow<Resource<AuthState>>
}