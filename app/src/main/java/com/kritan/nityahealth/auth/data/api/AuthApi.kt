package com.kritan.nityahealth.auth.data.api

import com.kritan.nityahealth.base.api.ApiResponse
import com.kritan.nityahealth.auth.data.models.AuthToken
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface AuthApi {
    @Multipart
    @POST("login")
    suspend fun login(
        @Part email: MultipartBody.Part,
        @Part password: MultipartBody.Part
    ): Response<ApiResponse<AuthToken>>

    @Multipart
    @POST("register")
    suspend fun register(
        @Part name: MultipartBody.Part,
        @Part email: MultipartBody.Part,
        @Part address: MultipartBody.Part,
        @Part phone: MultipartBody.Part,
        @Part password: MultipartBody.Part,
        @Part confirmPassword: MultipartBody.Part,
    ): Response<ApiResponse<AuthToken>>
}