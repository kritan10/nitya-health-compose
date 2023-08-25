package com.kritan.nityahealth.feature_fitness.presentation.exercise_home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_fitness.data.repository.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseHomeViewModel @Inject constructor(private val exerciseRepository: ExerciseRepository) :
    ViewModel() {

    var state by mutableStateOf(ExerciseHomeState())
        private set

    init {
        getAllFitness()
    }

    private fun getAllFitness() {
        viewModelScope.launch {
            exerciseRepository.getAllFitness().collect { response ->
                when (response) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> state = state.copy(isLoading = response.isLoading)
                    is Resource.Success -> response.data?.let {
                        state = state.copy(data = it)
                    }
                }
            }
        }
    }
}