package com.kritan.nityahealth.auth.data.api

import com.kritan.nityahealth.auth.data.models.FacebookUserData
import com.kritan.nityahealth.auth.data.models.FacebookUserName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FacebookApi {
    @GET("me/")
    suspend fun getUserName(@Query("access_token") accessToken: String): Response<FacebookUserName>

    @GET("{userId}/")
    suspend fun getUserData(
        @Path("userId") userId: String,
        @Query("fields") fields: String = "id,name,email,picture",
        @Query("access_token") accessToken: String,
    ): Response<FacebookUserData>
}