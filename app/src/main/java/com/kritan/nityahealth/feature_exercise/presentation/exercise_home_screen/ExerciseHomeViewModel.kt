package com.kritan.nityahealth.feature_exercise.presentation.exercise_home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_exercise.data.local.TrainingRepository
import com.kritan.nityahealth.feature_exercise.data.models.ExercisePackage
import com.kritan.nityahealth.feature_exercise.data.repository.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseHomeViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    val trainingRepository: TrainingRepository
) :
    ViewModel() {

    var state by mutableStateOf(ExerciseHomeState())
        private set

    init {
        getAllFitness()
    }

    private fun getCompletedTrainings(data: List<ExercisePackage>) {
        viewModelScope.launch {
            val myTrainings = trainingRepository.getAllTrainings()
            val myExercisePackage = mutableListOf<ExercisePackage>()
            data.forEach { exercise ->
                myTrainings.forEach { training ->
                    if (exercise.id == training.packageId) {
                        myExercisePackage.add(exercise)
                    }
                }
            }
            state = state.copy(myExercises = myExercisePackage, myTrainings = myTrainings)
        }
    }

    private fun getAllFitness() {
        viewModelScope.launch {
            exerciseRepository.getAllFitness().collect { response ->
                when (response) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> Unit
                    is Resource.Success -> response.data?.let {
                        state = state.copy(data = it)
                        getCompletedTrainings(it)
                        state = state.copy(isLoading = false)
                    }
                }
            }
        }
    }
}