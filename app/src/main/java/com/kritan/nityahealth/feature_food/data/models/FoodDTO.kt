package com.kritan.nityahealth.feature_food.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodDTO(
    @SerialName("posts")
    val food: List<Food>
)