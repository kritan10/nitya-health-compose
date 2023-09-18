package com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.questions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.utils.blankWhenNull
import com.kritan.nityahealth.ui.components.MyTextField

@Composable
fun FormPersonalInfo(
    personalInfoResponse: DoctorPersonalInfo,
    onPersonalInfoResponse: (DoctorPersonalInfo) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        MyTextField(
            label = "Full name",
            value = personalInfoResponse.name ?: "",
            onValueChange = {
                onPersonalInfoResponse(personalInfoResponse.copy(name = it))
            },
        )

        MyTextField(
            label = "Age",
            value = personalInfoResponse.age.blankWhenNull(),
            onValueChange = {
                onPersonalInfoResponse(personalInfoResponse.copy(age = it.toIntOrNull()))
            },
            keyboardType = KeyboardType.Number
        )

        MyTextField(
            label = "Address",
            value = personalInfoResponse.address ?: "",
            onValueChange = {
                onPersonalInfoResponse(personalInfoResponse.copy(address = it))
            },
        )

        MyTextField(
            label = "Contact",
            value = personalInfoResponse.contact ?: "",
            onValueChange = {
                onPersonalInfoResponse(personalInfoResponse.copy(contact = it))
            },
        )
    }
}