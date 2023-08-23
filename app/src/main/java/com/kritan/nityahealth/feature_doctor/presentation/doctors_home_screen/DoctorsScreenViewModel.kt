package com.kritan.nityahealth.feature_doctor.presentation.doctors_home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_doctor.domain.DoctorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorsScreenViewModel @Inject constructor(private val doctorRepository: DoctorRepository) :
    ViewModel() {

    var state by mutableStateOf(DoctorsScreenState())

    init {
        getAllDoctors()
    }

    private fun getAllDoctors() {
        viewModelScope.launch {
            doctorRepository
                .getAllDoctors()
                .collect { result ->
                    when (result) {
                        is Resource.Error -> Unit
                        is Resource.Loading -> state = state.copy(isLoading = result.isLoading)
                        is Resource.Success -> {
                            result.data?.let {
                                state = state.copy(doctors = it)
                            }
                        }
                    }
                }
        }
    }
}