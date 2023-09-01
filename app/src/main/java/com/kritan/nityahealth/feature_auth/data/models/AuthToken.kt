package com.kritan.nityahealth.feature_auth.data.models

import com.google.gson.annotations.SerializedName

data class AuthToken(
    @SerializedName("token")
    val token: String = "",

    @SerializedName("name")
    val name: String = ""
)