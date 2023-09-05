package com.kritan.nityahealth.feature_doctor.data.api

import com.kritan.nityahealth.base.api.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface DoctorsApi {

    @GET("alldoctors")
    suspend fun getAllDoctors(): Response<ApiResponse<DoctorDTO>>

    @GET("doctor/{id}")
    suspend fun getDoctorDetails(@Path("id") id: Int): Response<ApiResponse<DoctorDetailsDTO>>
}