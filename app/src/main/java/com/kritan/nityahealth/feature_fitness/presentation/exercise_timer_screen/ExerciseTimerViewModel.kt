package com.kritan.nityahealth.feature_fitness.presentation.exercise_timer_screen

import android.os.Build
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_fitness.data.local.Training
import com.kritan.nityahealth.feature_fitness.data.local.TrainingRepository
import com.kritan.nityahealth.feature_fitness.data.models.ExerciseTraining
import com.kritan.nityahealth.feature_fitness.data.repository.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ExerciseTimerViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val trainingRepository: TrainingRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    var state by mutableStateOf(ExerciseTimerState())
        private set
    private var exerciseId: Int


    init {
        val idString = savedStateHandle.get<String>("exercisePackageId")!!
        exerciseId = idString.toInt()
        getAllFitness()
        getAllTrainings()
    }

    fun onExerciseChange() {
        viewModelScope.launch {
            state = state.copy(
                timeLeft = 20,
            )
        }
    }

    fun onAddRestTime() {
        viewModelScope.launch {
            state = state.copy(
                timeLeft = state.timeLeft + 20,
            )
        }
    }


    suspend fun onTick() {
        while (state.timeLeft > 0 && !state.isPaused) {
            delay(1000L)
            state = state.copy(
                timeLeft = state.timeLeft - 1
            )
        }
    }

    fun onToggle(pause:Boolean) {
        state = state.copy(
            isPaused = pause
        )
    }

    fun onTrainingComplete(){
        viewModelScope.launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                trainingRepository.insertTraining(
                    Training(
                        packageId = exerciseId,
                        completedOn = LocalDate.now()
                    )
                )
            }
        }
    }

    private fun getAllFitness() {
        viewModelScope.launch {
            exerciseRepository.getAllFitness().collect { response ->
                when (response) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> Unit
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
                    is Resource.Loading -> Unit
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
                                        newList.add(
                                            mTraining.copy(
                                                trainingName = "Rest",
                                                trainingType = "Rest",
                                                duration = "20"
                                            )
                                        )
                                    }
                                    newList.removeLast()
                                    val timeLeft = newList[0].duration?.toInt()
                                    state = state.copy(
                                        trainings = newList,
                                        currentTraining = newList[0],
                                        timeLeft = timeLeft!!
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
