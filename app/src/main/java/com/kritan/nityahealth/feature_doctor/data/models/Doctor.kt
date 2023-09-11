package com.kritan.nityahealth.feature_doctor.data.models

import com.google.gson.annotations.SerializedName


data class Doctor(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("position")
    val position: String?,

    @SerializedName("phone")
    val phone: String?,

    @SerializedName("experince")
    val experience: String?,

    @SerializedName("Qualificaton")
    val qualification: String?,

    @SerializedName("speciality")
    val speciality: String?,

    @SerializedName("NMC_number")
    val nmc: String?,

    @SerializedName("website")
    val website: String?,

    @SerializedName("image")
    val image: String?
)