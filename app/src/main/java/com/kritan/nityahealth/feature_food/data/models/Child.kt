package com.kritan.nityahealth.feature_food.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Child(
    val id: Int,
    val posts: List<PostX>,
    val title: String
)