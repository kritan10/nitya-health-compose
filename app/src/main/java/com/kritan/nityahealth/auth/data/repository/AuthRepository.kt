package com.kritan.nityahealth.auth.data.repository

import com.kritan.nityahealth.auth.data.models.AuthState
import com.kritan.nityahealth.auth.data.models.AuthUserData
import com.kritan.nityahealth.base.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(userLogin: AuthUserData.UserLogin): Flow<Resource<AuthState>>

    suspend fun register(userRegister: AuthUserData.UserRegister): Flow<Resource<AuthState>>
}