package com.kritan.nityahealth.feature_wellness.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class WellnessViewModel : ViewModel() {
    var currentTab by mutableStateOf<WellnessTab>(WellnessTab.Fitness)

    private val _uiEvent = Channel<UiEvent> { }
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onTabClick(newValue: WellnessTab) {
        viewModelScope.launch {
            currentTab = newValue
            val index = when (newValue) {
                WellnessTab.Fitness -> 0
                WellnessTab.Food -> 1
                WellnessTab.HealthTopics -> 3
                WellnessTab.PersonalCare -> 2
            }
            _uiEvent.send(UiEvent.ScrollToItem(index))
        }
    }
}


sealed class WellnessTab(val title: String) {
    object Fitness : WellnessTab("Fitness")
    object Food : WellnessTab("Food")
    object PersonalCare : WellnessTab("Personal Care")
    object HealthTopics : WellnessTab("Health Topics")
}

sealed class UiEvent {
    data class ScrollToItem(val index: Int) : UiEvent()
}