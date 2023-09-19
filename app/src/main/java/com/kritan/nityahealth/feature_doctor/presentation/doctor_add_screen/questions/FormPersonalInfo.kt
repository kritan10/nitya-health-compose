package com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.questions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.base.extensions.blankWhenNull
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.models.DoctorPersonalInfo
import com.kritan.nityahealth.ui.components.MyTextField

@Composable
fun FormPersonalInfo(
    personalInfoResponse: DoctorPersonalInfo,
    onPersonalInfoResponse: (DoctorPersonalInfo) -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("General Information", style = MaterialTheme.typography.titleLarge)

        MyTextField(
            label = "Full name",
            value = personalInfoResponse.name ?: "",
            onValueChange = {
                onPersonalInfoResponse(personalInfoResponse.copy(name = it))
            },
            placeholder = "e.g. John Doe",
        )

        MyTextField(
            label = "Age",
            value = personalInfoResponse.age.blankWhenNull(),
            onValueChange = {
                onPersonalInfoResponse(personalInfoResponse.copy(age = it.toIntOrNull()))
            },
            placeholder = "e.g 42",
            keyboardType = KeyboardType.Number
        )

        MyTextField(
            label = "Address",
            value = personalInfoResponse.address ?: "",
            onValueChange = {
                onPersonalInfoResponse(personalInfoResponse.copy(address = it))
            },
            placeholder = "Your current living address",
        )

        MyTextField(
            label = "Contact",
            value = personalInfoResponse.contact ?: "",
            onValueChange = {
                onPersonalInfoResponse(personalInfoResponse.copy(contact = it))
            },
            placeholder = "Your personal contact number",
            isLastField = true
        )
    }
}