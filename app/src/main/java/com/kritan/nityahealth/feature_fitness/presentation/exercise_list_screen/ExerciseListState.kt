package com.kritan.nityahealth.feature_fitness.presentation.exercise_list_screen

import com.kritan.nityahealth.feature_fitness.data.models.ExercisePackage

data class ExerciseListState(
    val isLoading: Boolean = false,
    val exercise: ExercisePackage = ExercisePackage()
) {

}