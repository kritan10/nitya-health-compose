package com.kritan.nityahealth.feature_wellness.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class WellnessViewModel : ViewModel() {
    val wellnessTabItemsList = listOf(
        WellnessTab.Fitness,
        WellnessTab.Food,
        WellnessTab.PersonalCare,
        WellnessTab.HealthTopics,
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
    object Fitness : WellnessTab("Fitness", 0)
    object Food : WellnessTab("Food", 1)
    object PersonalCare : WellnessTab("Personal Care", 2)
    object HealthTopics : WellnessTab("Health Topics", 3)
}

sealed class UiEvent {
    data class ScrollTo(val index: Int) : UiEvent()
}