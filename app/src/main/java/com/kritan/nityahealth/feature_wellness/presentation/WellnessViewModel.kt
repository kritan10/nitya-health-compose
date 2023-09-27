package com.kritan.nityahealth.feature_wellness.presentation

import androidx.lifecycle.ViewModel

class WellnessViewModel : ViewModel() {
    val wellnessTabItemsList = listOf(
        WellnessTab.FitnessTab,
        WellnessTab.FoodTab,
        WellnessTab.PersonalCareTab,
        WellnessTab.HealthTopicsTab,
    )
}

sealed class WellnessTab(val title: String, val position: Int) {
    object FitnessTab : WellnessTab("Fitness", 0)
    object FoodTab : WellnessTab("Food", 1)
    object PersonalCareTab : WellnessTab("Personal Care", 2)
    object HealthTopicsTab : WellnessTab("Health Topics", 3)
}