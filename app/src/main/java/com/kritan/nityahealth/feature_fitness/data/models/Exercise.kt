package com.kritan.nityahealth.feature_fitness.data.models

import com.google.gson.annotations.SerializedName

data class Exercise(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("page_id")
    val pageId: String = "",

    @SerializedName("short")
    val short: String = "",

    @SerializedName("full")
    val full: String = "",

    @SerializedName("title")
    val title: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("image")
    val image: String = "",

    @SerializedName("created_at")
    val createdAt: String = ""

)