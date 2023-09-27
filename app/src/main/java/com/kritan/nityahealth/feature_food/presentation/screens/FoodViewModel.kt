package com.kritan.nityahealth.feature_food.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_food.data.models.Food
import com.kritan.nityahealth.feature_food.data.repository.FoodRepository
import com.kritan.nityahealth.feature_food.presentation.models.FoodState
import com.kritan.nityahealth.feature_food.presentation.models.FoodUserDetail
import com.kritan.nityahealth.feature_food.presentation.models.enums.Activeness
import com.kritan.nityahealth.feature_food.presentation.models.enums.DietType
import com.kritan.nityahealth.feature_food.presentation.models.enums.Gender
import com.kritan.nityahealth.feature_food.presentation.models.enums.HeightUnit
import com.kritan.nityahealth.feature_food.presentation.models.enums.WeightGoal
import com.kritan.nityahealth.feature_food.presentation.models.enums.WeightUnit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    var uiState by mutableStateOf(FoodState())
        private set

    private val gson: Gson = GsonBuilder().create()
    private val foodPrefKey = stringPreferencesKey("food")

    init {
        getFoodPreferences()
        getFoodData()
    }

    fun initializeFoodUserDetail() {
        uiState = uiState.copy(foodUserDetail = FoodUserDetail())
    }

    private fun getFoodData() {
        viewModelScope.launch {
            foodRepository.getAllFood().collect { response ->
                when (response) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> uiState = uiState.copy(isLoading = response.isLoading)
                    is Resource.Success -> {
                        response.data?.let { foodList ->
                            uiState = uiState.copy(
                                foodData = getCategorizedFoodList(foodList)
                            )
                        }
                    }

                }
            }
        }
    }

    private fun getFoodPreferences() {
        viewModelScope.launch {
            val foodUserDetail = dataStore.data.map {
                gson.fromJson(it[foodPrefKey], FoodUserDetail::class.java) ?: null
            }.first()
            uiState = uiState.copy(foodUserDetail = foodUserDetail)
        }
    }

    fun saveCurrentFoodPreferences() {
        viewModelScope.launch {
            dataStore.edit {
                it[foodPrefKey] = gson.toJson(uiState.foodUserDetail)
            }
        }
    }

    fun selectWeightGoal(weightGoal: WeightGoal) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail?.copy(
                weightGoal = weightGoal
            )
        )
    }

    fun onHeightChange(value: String) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail?.copy(
                height = value.toIntOrNull()
            )
        )
    }

    fun onHeightUnitChange(value: HeightUnit) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail?.copy(
                heightUnit = value
            )
        )
    }

    fun onWeightChange(value: String) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail?.copy(
                weight = value.toIntOrNull()
            )
        )
    }

    fun onWeightUnitChange(value: WeightUnit) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail?.copy(
                weightUnit = value
            )
        )
    }

    fun onAgeChange(value: String) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail?.copy(
                age = value.toIntOrNull()
            )
        )
    }

    fun onGenderChange(value: Gender) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail?.copy(
                gender = value
            )
        )
    }

    fun onDietTypeChange(value: DietType) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail?.copy(
                dietType = value
            )
        )
    }

    fun onActivenessChange(value: Activeness) {
        uiState = uiState.copy(
            foodUserDetail = uiState.foodUserDetail?.copy(
                activeness = value
            )
        )
    }

    private fun getCategorizedFoodList(items: List<Food>): List<List<Food>> {
        val weightGainFoodList: MutableList<Food> = mutableListOf()
        val weightLossFoodList: MutableList<Food> = mutableListOf()
        val noCategoryFoodList: MutableList<Food> = mutableListOf()

        items.forEach {
            val title = it.title
            if (title.contains("gain", ignoreCase = true)) {
                weightGainFoodList.add(it)
            } else if (title.contains("loss", ignoreCase = true)) {
                weightLossFoodList.add(it)
            } else {
                noCategoryFoodList.add(it)
            }
        }

        return listOf(
            weightGainFoodList.toList(),
            weightLossFoodList.toList(),
            noCategoryFoodList.toList()
        )
    }
}


