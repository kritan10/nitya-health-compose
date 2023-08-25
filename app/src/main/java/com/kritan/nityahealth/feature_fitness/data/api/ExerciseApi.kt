package com.kritan.nityahealth.feature_fitness.data.api

import com.kritan.nityahealth.base.api.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ExerciseApi {

    @GET("fitness/16/allpost/")
    suspend fun getAllExercises():Response<ApiResponse<ExerciseDTO>>
}