package com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.questions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.models.DoctorPersonalInfo
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.models.DoctorProfessionalInfo

@Composable
fun FormDetailsPreview(
    personalInfo: DoctorPersonalInfo,
    professionalInfo: DoctorProfessionalInfo
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Text("General Information")
        Text("name: ${personalInfo.name}")
        Text("age: ${personalInfo.age}")
        Text("address: ${personalInfo.address}")
        Text("contact: ${personalInfo.contact}")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Professional Information")
        Text("major: ${professionalInfo.major}")
        Text("certificateNumber: ${professionalInfo.certificateNumber}")
        Text("specialization: ${professionalInfo.specialization}")
        Text("experience: ${professionalInfo.experience}")
    }
}