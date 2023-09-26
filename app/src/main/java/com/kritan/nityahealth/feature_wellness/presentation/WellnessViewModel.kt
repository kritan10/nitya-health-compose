package com.kritan.nityahealth.feature_wellness.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class WellnessViewModel : ViewModel() {
    val wellnessTabItemsList = listOf(
        WellnessTab.FitnessTab,
        WellnessTab.FoodTab,
        WellnessTab.PersonalCareTab,
        WellnessTab.HealthTopicsTab,
    )

    private val _uiEvent = Channel<UiEvent> { }
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onTabChange(newTabIndex: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _uiEvent.send(UiEvent.ScrollTo(newTabIndex))
        }
    }
}


sealed class WellnessTab(val title: String, val position: Int) {
    object FitnessTab : WellnessTab("Fitness", 0)
    object FoodTab : WellnessTab("com.kritan.nityahealth.feature_food.data.models.Food", 1)
    object PersonalCareTab : WellnessTab("Personal Care", 2)
    object HealthTopicsTab : WellnessTab("Health Topics", 3)
}

sealed class UiEvent {
    data class ScrollTo(val index: Int) : UiEvent()
}