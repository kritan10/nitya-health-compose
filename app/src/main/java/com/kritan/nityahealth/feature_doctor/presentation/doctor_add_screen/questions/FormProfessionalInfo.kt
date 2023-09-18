package com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.questions

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.utils.blankWhenNull
import com.kritan.nityahealth.ui.components.MyButton
import com.kritan.nityahealth.ui.components.MyTextField

@Composable
fun FormProfessionalInfo(
    professionalInfo: DoctorProfessionalInfo,
    onResponse: (DoctorProfessionalInfo) -> Unit
) {

    var pdfFileUri by remember {
        mutableStateOf(Uri.EMPTY)
    }

    val pdfFilePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                pdfFileUri = uri
            }
        }
    )

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        MyTextField(
            label = "Major",
            value = professionalInfo.major ?: "",
            onValueChange = {
                onResponse(professionalInfo.copy(major = it))
            },
        )

        MyTextField(
            label = "Certificate Number",
            value = professionalInfo.certificateNumber.blankWhenNull(),
            onValueChange = {
                onResponse(professionalInfo.copy(certificateNumber = it.toIntOrNull()))
            },
        )

        MyTextField(
            label = "Specialization",
            value = professionalInfo.specialization ?: "",
            onValueChange = {
                onResponse(professionalInfo.copy(specialization = it))
            },
        )

        MyTextField(
            label = "Experience in Years",
            value = professionalInfo.experience.blankWhenNull(),
            onValueChange = {
                onResponse(professionalInfo.copy(experience = it.toIntOrNull()))
            },
        )

        MyButton(label = "Upload certificate PDF") {
            pdfFilePicker.launch("application/pdf")
        }

        if (pdfFileUri.path?.isNotEmpty() == true) {
            Text(pdfFileUri.path.toString())
        }
    }
}

