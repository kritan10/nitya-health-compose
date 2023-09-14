package com.kritan.nityahealth.auth.data.api

import com.kritan.nityahealth.base.api.ApiResponse
import com.kritan.nityahealth.auth.data.models.AuthToken
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * Retrofit interface for authentication. It is used for logging in and registration.
 */
interface AuthApi {
    /**
     * POST request that sends the email and password to the server and receives an [AuthToken].
     * The request data is sent in a multipart/form-data form.
     *
     * @param email the email address of the registered user
     * @param password the password of the registered user
     *
     * @return [AuthToken], an authentication token containing user data and an auth token.
     * This token can be used to send request to authenticated routes.
     * The response is wrapped in a generic [ApiResponse] which is again wrapped in [retrofit2.Response]
     */
    @Multipart
    @POST("login")
    suspend fun login(
        @Part email: MultipartBody.Part,
        @Part password: MultipartBody.Part
    ): Response<ApiResponse<AuthToken>>


    /**
     * POST request that sends the user data for registration to the server and receives an [AuthToken].
     * The request data is sent in a multipart/form-data form.
     *
     * @param name name of the user
     * @param email email address of the user
     * @param address physical address of the user
     * @param phone phone number of the user
     * @param password new password of the user
     * @param confirmPassword confirmation password, must be same as [password]
     *
     * @return [AuthToken], an authentication token containing user data and an auth token.
     * This token can be used to send request to authenticated routes.
     * The response is wrapped in a generic [ApiResponse] which is again wrapped in [retrofit2.Response]
     */
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