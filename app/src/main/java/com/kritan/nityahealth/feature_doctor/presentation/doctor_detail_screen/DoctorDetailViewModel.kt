package com.kritan.nityahealth.feature_doctor.presentation.doctor_detail_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_doctor.data.repository.DoctorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorDetailViewModel @Inject constructor(
    private val doctorRepository: DoctorRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    var state by mutableStateOf(DoctorDetailScreenState())

    init {
        val id = savedStateHandle.get<Int>("id")!!
        getDoctorDetails(id)
    }


    private fun getDoctorDetails(id: Int) {
        viewModelScope.launch {
            doctorRepository
                .getDoctorDetail(id)
                .collect { result ->
                    when (result) {
                        is Resource.Error -> {}
                        is Resource.Loading -> state = state.copy(isLoading = result.isLoading)
                        is Resource.Success -> {
                            state = state.copy(doctor = result.data!!)
                        }
                    }
                }
        }
    }
}