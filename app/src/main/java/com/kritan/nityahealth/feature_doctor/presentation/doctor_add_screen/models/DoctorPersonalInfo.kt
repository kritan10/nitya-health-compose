package com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.models

data class DoctorPersonalInfo(
    val name: String? = null,
    val age: Int? = null,
    val address: String? = null,
    val contact: String? = null,
) {
    companion object {
        fun validateDoctorPersonalInfo(personalInfo: DoctorPersonalInfo): Boolean {
            with(personalInfo) {
                return !name.isNullOrEmpty() &&
                        !address.isNullOrEmpty() &&
                        !contact.isNullOrEmpty() &&
                        age != null
            }
        }
    }
}