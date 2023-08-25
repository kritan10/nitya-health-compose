package com.kritan.nityahealth.feature_fitness.presentation.exercise_home_screen

import com.kritan.nityahealth.feature_fitness.data.models.ExercisePackage

data class ExerciseHomeState(val isLoading: Boolean = false, val data: List<ExercisePackage> = emptyList())