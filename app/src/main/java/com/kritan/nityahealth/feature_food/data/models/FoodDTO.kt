package com.kritan.nityahealth.feature_food.data.models

import kotlinx.serialization.Serializable

@Serializable
data class FoodDTO(val food: List<Food>) {

}