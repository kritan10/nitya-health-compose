package com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.models

import android.net.Uri

data class DoctorProfessionalInfo(
    val major: String? = null,
    val certificateNumber: Int? = null,
    val specialization: String? = null,
    val experience: Int? = null,
    val pdfFileUri: Uri? = null
) {
    companion object {
        fun validateDoctorProfessionalInfo(professionalInfo: DoctorProfessionalInfo): Boolean {
            with(professionalInfo) {
                return !major.isNullOrEmpty() &&
                        !specialization.isNullOrEmpty() &&
                        certificateNumber != null &&
                        experience != null &&
                        pdfFileUri != null
            }
        }
    }
}