package com.kritan.nityahealth.feature_doctor.data.models

import com.google.gson.annotations.SerializedName

data class DoctorDetail(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("email")
    val email: String = "",

    @SerializedName("position")
    val position: String = "",

    @SerializedName("phone")
    val phone: String = "",

    @SerializedName("NMC_number")
    val nmc: String = "",

    @SerializedName("website")
    val website: String = "",

    @SerializedName("image")
    val image: String = "",

    @SerializedName("location")
    val location: List<DoctorLocation> = emptyList(),

    @SerializedName("experinces")
    val experiences: List<String> = emptyList(),

    @SerializedName("specialities")
    val specialities: List<String> = emptyList(),

    @SerializedName("qualifications")
    val qualifications: List<DoctorQualification> = emptyList()
)


