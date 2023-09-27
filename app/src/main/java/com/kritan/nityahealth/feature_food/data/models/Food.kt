package com.kritan.nityahealth.feature_food.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Food(
    @SerialName("created_at")
    val createdAt: String,

    val description: String,
    val full: String,
    val id: Int,
    val image: String,

    @SerialName("page_id")
    val pageId: String,

    val title: String
)