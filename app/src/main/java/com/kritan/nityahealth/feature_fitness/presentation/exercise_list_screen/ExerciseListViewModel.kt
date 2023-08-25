package com.kritan.nityahealth.feature_fitness.presentation.exercise_list_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_fitness.data.repository.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    var state by mutableStateOf(ExerciseListState())
        private set

    var exerciseId: Int

    init {
        val idString = savedStateHandle.get<String>("exercisePackageId")!!
        exerciseId = idString.toInt()
        getAllFitness()
    }

    private fun getAllFitness() {
        viewModelScope.launch {
            exerciseRepository.getAllFitness().collect { response ->
                when (response) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> state = state.copy(isLoading = response.isLoading)
                    is Resource.Success -> response.data?.let { list ->
                        val exercise = list.find {
                            it.id == exerciseId
                        }
                        exercise?.let {
                            state = state.copy(exercise = exercise)
                        }
                    }
                }
            }
        }
    }

}