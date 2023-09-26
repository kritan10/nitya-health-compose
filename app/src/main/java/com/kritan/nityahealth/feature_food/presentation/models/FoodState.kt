package com.kritan.nityahealth.feature_food.presentation.models

import com.kritan.nityahealth.feature_food.data.models.Food
import com.kritan.nityahealth.feature_food.presentation.models.enums.WeightGoal

data class FoodState(
    val isLoading: Boolean = false,
    val foodData: List<Food>? = null,
    val weightGoal: WeightGoal? = null,
    val foodUserDetail: FoodUserDetail = FoodUserDetail()
)