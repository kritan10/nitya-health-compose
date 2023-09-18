package com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.questions

data class DoctorPersonalInfo(
    val name: String? = null,
    val age: Int? = null,
    val address: String? = null,
    val contact: String? = null,
)

data class DoctorProfessionalInfo(
    val major: String? = null,
    val certificateNumber: Int? = null,
    val specialization: String? = null,
    val experience: Int? = null,
)