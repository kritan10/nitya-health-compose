package com.kritan.nityahealth.feature_doctor.data.api

import com.google.gson.annotations.SerializedName
import com.kritan.nityahealth.feature_doctor.data.models.DoctorDetail

data class DoctorDetailsDTO(
    @SerializedName("doctor")
    val doctor: DoctorDetail = DoctorDetail()
)