package com.kritan.nityahealth.feature_food.presentation.models

import com.kritan.nityahealth.feature_food.data.models.Food

data class FoodState(
    val isLoading: Boolean = false,
    val foodData: List<List<Food>>? = null,
    val foodUserDetail: FoodUserDetail? = null
)