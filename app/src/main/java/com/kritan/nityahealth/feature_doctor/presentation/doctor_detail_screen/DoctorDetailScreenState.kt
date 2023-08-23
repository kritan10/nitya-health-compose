package com.kritan.nityahealth.feature_doctor.presentation.doctor_detail_screen

import com.kritan.nityahealth.feature_doctor.data.models.DoctorDetail

data class DoctorDetailScreenState(
    val isLoading: Boolean = false,
    val doctor: DoctorDetail? = null
)