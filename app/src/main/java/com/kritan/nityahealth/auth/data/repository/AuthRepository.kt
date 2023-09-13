package com.kritan.nityahealth.auth.data.repository

import android.app.Activity
import com.kritan.nityahealth.auth.data.models.AuthState
import com.kritan.nityahealth.auth.data.models.AuthUserData
import com.kritan.nityahealth.base.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun loginWithEmail(userLogin: AuthUserData.UserLogin): Flow<Resource<AuthState>>

    suspend fun registerWithEmail(userRegister: AuthUserData.UserRegister): Flow<Resource<AuthState>>

    suspend fun loginWithFacebook(activity: Activity): Resource<AuthState>
}