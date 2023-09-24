package com.kritan.nityahealth.feature_food.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kritan.nityahealth.feature_food.presentation.models.FoodState
import com.kritan.nityahealth.feature_food.presentation.models.enums.Activeness
import com.kritan.nityahealth.feature_food.presentation.models.enums.DietType
import com.kritan.nityahealth.feature_food.presentation.models.enums.Gender
import com.kritan.nityahealth.feature_food.presentation.models.enums.HeightUnit
import com.kritan.nityahealth.feature_food.presentation.models.enums.WeightGoal
import com.kritan.nityahealth.feature_food.presentation.models.enums.WeightUnit
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor() : ViewModel() {
    var uiState by mutableStateOf(FoodState())
        private set

    fun selectWeightGoal(weightGoal: WeightGoal) {
        uiState = uiState.copy(
            weightGoal = weightGoal
        )
    }

    fun onHeightChange(value: String) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail.copy(
                height = value.toIntOrNull()
            )
        )
    }

    fun onHeightUnitChange(value: HeightUnit) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail.copy(
                heightUnit = value
            )
        )
    }

    fun onWeightChange(value: String) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail.copy(
                weight = value.toIntOrNull()
            )
        )
    }

    fun onWeightUnitChange(value: WeightUnit) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail.copy(
                weightUnit = value
            )
        )
    }

    fun onAgeChange(value: String) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail.copy(
                age = value.toIntOrNull()
            )
        )
    }

    fun onGenderChange(value: Gender) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail.copy(
                gender = value
            )
        )
    }

    fun onDietTypeChange(value: DietType) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail.copy(
                dietType = value
            )
        )
    }

    fun onActivenessChange(value: Activeness){
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail.copy(
                activeness = value
            )
        )
    }
}


