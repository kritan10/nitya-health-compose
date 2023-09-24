package com.kritan.nityahealth.feature_food.presentation.models

import com.kritan.nityahealth.feature_food.presentation.models.enums.WeightGoal

data class FoodState(
    val weightGoal: WeightGoal? = null,
    val foodUserDetail: FoodUserDetail = FoodUserDetail()
)