package com.kritan.nityahealth.feature_exercise.data.api

import com.kritan.nityahealth.base.api.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExerciseApi {
    @GET("fitness/16/allpost/")
    suspend fun getAllExercises(): Response<ApiResponse<ExerciseDTO>>

    @GET("fitness/post/{id}/package/")
    suspend fun getExerciseBridge(@Path("id") id: Int): Response<ApiResponse<ExerciseBridgeDTO>>

    @GET("fitness/post/package/{id}/trainings/")
    suspend fun getTraining(@Path("id") id: Int): Response<ApiResponse<ExerciseTrainingDTO>>
}