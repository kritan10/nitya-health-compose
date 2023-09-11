package com.kritan.nityahealth.feature_doctor.data.api

import com.google.gson.annotations.SerializedName
import com.kritan.nityahealth.feature_doctor.data.models.Doctor

data class DoctorDTO(
    @SerializedName("doctors")
    val doctors: List<Doctor> = emptyList()
)