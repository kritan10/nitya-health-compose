package com.kritan.nityahealth.feature_exercise.presentation.exercise_list_screen

import com.kritan.nityahealth.feature_exercise.data.models.ExercisePackage
import com.kritan.nityahealth.feature_exercise.data.models.ExerciseTraining

data class ExerciseListState(
    val isLoading: Boolean = false,
    val exercise: ExercisePackage = ExercisePackage(),
    val trainings: List<ExerciseTraining> = emptyList()
)