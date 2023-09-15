package com.kritan.nityahealth.auth.data.models

import com.google.gson.annotations.SerializedName

data class FacebookUserName(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("name")
    val name: String? = null,

)