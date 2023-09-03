package com.kritan.nityahealth.feature_exercise.presentation.exercise_home_screen

import com.kritan.nityahealth.feature_exercise.data.local.Training
import com.kritan.nityahealth.feature_exercise.data.models.ExercisePackage

data class ExerciseHomeState(
    val isLoading: Boolean = true,
    val data: List<ExercisePackage> = emptyList(),
    val myExercises: List<ExercisePackage> = emptyList(),
    val myTrainings: List<Training> = emptyList()

)