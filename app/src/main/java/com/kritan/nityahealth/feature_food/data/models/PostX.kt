package com.kritan.nityahealth.feature_food.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostX(
    @SerialName("created_at")
    val created_at: String,
    val description: String,
    val full: String,
    val id: Int,
    val image: String,
    val page_id: String,
    val title: String
)