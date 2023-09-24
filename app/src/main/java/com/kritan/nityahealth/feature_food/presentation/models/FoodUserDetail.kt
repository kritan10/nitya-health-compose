package com.kritan.nityahealth.feature_food.presentation.models

import com.kritan.nityahealth.feature_food.presentation.models.enums.Activeness
import com.kritan.nityahealth.feature_food.presentation.models.enums.DietType
import com.kritan.nityahealth.feature_food.presentation.models.enums.Gender
import com.kritan.nityahealth.feature_food.presentation.models.enums.HeightUnit
import com.kritan.nityahealth.feature_food.presentation.models.enums.WeightUnit

data class FoodUserDetail(
    val height: Int? = null,
    val heightUnit: HeightUnit = HeightUnit.FEET_INCH,
    val weight: Int? = null,
    val weightUnit: WeightUnit = WeightUnit.LBS,
    val age: Int? = null,
    val gender: Gender? = null,
    val activeness: Activeness? = null,
    val dietType: DietType? = null
)

