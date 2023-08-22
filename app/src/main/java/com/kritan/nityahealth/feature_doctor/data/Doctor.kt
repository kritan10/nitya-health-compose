package com.kritan.nityahealth.feature_doctor.data

import com.google.gson.annotations.SerializedName


data class Doctor(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("position")
    val position: String? = null,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("experince")
    val experience: String? = null,

    @SerializedName("Qualificaton")
    val qualification: String? = null,

    @SerializedName("speciality")
    val speciality: String? = null,

    @SerializedName("NMC_number")
    val nmc: String? = null,

    @SerializedName("website")
    val website: String? = null,

    @SerializedName("image")
    val image: String? = null
)