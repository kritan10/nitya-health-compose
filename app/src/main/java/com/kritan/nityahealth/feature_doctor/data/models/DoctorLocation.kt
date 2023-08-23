package com.kritan.nityahealth.feature_doctor.data.models

import com.google.gson.annotations.SerializedName

data class DoctorLocation(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("address")
    val address: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("image")
    val image: String? = null
)
