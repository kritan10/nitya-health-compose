package com.kritan.nityahealth.feature_exercise.presentation.exercise_timer_screen

import com.kritan.nityahealth.feature_exercise.data.models.ExercisePackage
import com.kritan.nityahealth.feature_exercise.data.models.ExerciseTraining

data class ExerciseTimerState(
    val isLoading: Boolean = true,
    val exercise: ExercisePackage = ExercisePackage(),
    val trainings: List<ExerciseTraining> = emptyList(),
    val currentTraining: ExerciseTraining = ExerciseTraining(),
    val timeLeft: Int = 0,
    val isPaused: Boolean = true,
)
