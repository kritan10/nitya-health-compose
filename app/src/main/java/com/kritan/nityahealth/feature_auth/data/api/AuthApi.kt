package com.kritan.nityahealth.feature_auth.data.api

import com.kritan.nityahealth.base.api.ApiResponse
import com.kritan.nityahealth.feature_auth.data.models.AuthToken
import com.kritan.nityahealth.feature_auth.data.models.UserLogin
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST


interface AuthApi {
    @Multipart
    @POST("login/")
    fun login(@Body userLoginData: RequestBody): Call<ApiResponse<AuthToken>>

    @Multipart
    @POST("register/")
    fun register(userRegisterData: RequestBody): Call<ApiResponse<AuthToken>>
}