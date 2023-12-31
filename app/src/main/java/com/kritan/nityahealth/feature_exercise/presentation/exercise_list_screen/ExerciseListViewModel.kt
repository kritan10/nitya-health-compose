package com.kritan.nityahealth.feature_exercise.presentation.exercise_list_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_exercise.data.models.ExerciseTraining
import com.kritan.nityahealth.feature_exercise.data.repository.ExerciseRepository
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

    private var exerciseId: Int

    init {
        val idString = savedStateHandle.get<String>("exercisePackageId")!!
        exerciseId = idString.toInt()
        getAllFitness()
        getAllTrainings()
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

    private fun getAllTrainings() {
        viewModelScope.launch {
            exerciseRepository.getExerciseBridge(exerciseId).collect { response ->
                when (response) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> state = state.copy(isLoading = response.isLoading)
                    is Resource.Success -> response.data?.let {
                        val id = it[0].id!!
                        exerciseRepository.getTraining(id).collect { response ->
                            when (response) {
                                is Resource.Error -> Unit
                                is Resource.Loading -> state =
                                    state.copy(isLoading = response.isLoading)

                                is Resource.Success -> response.data?.let { trainings ->
                                    val newList = mutableListOf<ExerciseTraining>()
                                    val mTraining = trainings[0]
                                    repeat(5) { index ->
                                        newList.add(
                                            mTraining.copy(
                                                trainingName = "${mTraining.post} - ${index + 1}",
                                            )
                                        )
                                    }
                                    val timeLeft = newList[0].duration?.toInt()
                                    state = state.copy(
                                        trainings = newList,
                                        )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}