package com.kritan.nityahealth.feature_doctor.presentation.doctors_home_screen

import com.kritan.nityahealth.feature_doctor.data.models.Doctor

data class DoctorsScreenState(
    val doctors: List<Doctor> = emptyList(),
    val isLoading: Boolean = false
)