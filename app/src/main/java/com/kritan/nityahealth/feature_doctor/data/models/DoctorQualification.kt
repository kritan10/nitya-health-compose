package com.kritan.nityahealth.feature_doctor.data.models

import com.google.gson.annotations.SerializedName

data class DoctorQualification(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("user_id")
    val userId: Int? = null,

    @SerializedName("institute")
    val institute: String? = null,

    @SerializedName("specialization")
    val specialization: String? = null,

    @SerializedName("date")
    val date: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null
)