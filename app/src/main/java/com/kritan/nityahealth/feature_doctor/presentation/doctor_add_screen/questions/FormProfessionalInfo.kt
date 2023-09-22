package com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.questions

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.base.extensions.blankWhenNull
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.models.DoctorProfessionalInfo
import com.kritan.nityahealth.ui.components.MyButton
import com.kritan.nityahealth.ui.components.MyTextField
import com.kritan.nityahealth.ui.theme.mRoundedCornerMD


const val MIME_TYPE_PDF = "application/pdf"

@Composable
fun FormProfessionalInfo(
    professionalInfo: DoctorProfessionalInfo,
    onResponse: (DoctorProfessionalInfo) -> Unit
) {
    val context = LocalContext.current

    val pdfFilePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                Log.d("URI", "$it")
                onResponse(professionalInfo.copy(pdfFileUri = uri))
            }
        }
    )

//    val bounds = RectangularBounds.newInstance(
//        LatLng(80.0884245137, 26.3978980576),
//        LatLng(88.1748043151, 30.4227169866)
//    )
//    val TAG = "Places"
//
//    val placesClient = Places.createClient(context)
//
//    val token = AutocompleteSessionToken.newInstance()
//
//    fun createRequest(query: String): FindAutocompletePredictionsRequest {
//        return FindAutocompletePredictionsRequest.builder()
//            .setLocationBias(bounds)
//            .setTypesFilter(listOf(PlaceTypes.ADDRESS))
//            .setQuery(query)
//            .setSessionToken(token)
//            .build()
//    }


    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Professional Information", style = MaterialTheme.typography.titleLarge)

        MyTextField(
            label = "Current Degree",
            value = professionalInfo.major ?: "",
            onValueChange = {
                onResponse(professionalInfo.copy(major = it))
            },
            placeholder = "e.g MBBS, MD",
        )

        MyTextField(
            label = "Certificate Number",
            value = professionalInfo.certificateNumber.blankWhenNull(),
            onValueChange = {
                onResponse(professionalInfo.copy(certificateNumber = it.toIntOrNull()))
            },
            placeholder = "e.g 1208437",
            keyboardType = KeyboardType.Number
        )

        MyTextField(
            label = "Specialization",
            value = professionalInfo.specialization ?: "",
            onValueChange = {
//                placesClient.findAutocompletePredictions(createRequest(it))
//                    .addOnSuccessListener { response: FindAutocompletePredictionsResponse ->
//                        for (prediction in response.autocompletePredictions) {
//                            Log.i(TAG, prediction.placeId)
//                            Log.i(TAG, prediction.getPrimaryText(null).toString())
//                        }
//                    }.addOnFailureListener { exception: Exception? ->
//                        if (exception is ApiException) {
//                            exception.printStackTrace()
//                            Log.e(TAG, "Place not found: ${exception.statusCode}")
//                        }
//                    }

                onResponse(professionalInfo.copy(specialization = it))
            },
            placeholder = "e.g Orthopedics, Neurology",
        )

        MyTextField(
            label = "Working Experience",
            value = professionalInfo.experience.blankWhenNull(),
            onValueChange = {
                onResponse(professionalInfo.copy(experience = it.toIntOrNull()))
            },
            isLastField = true,
            placeholder = "e.g 3 years",
            keyboardType = KeyboardType.Number
        )

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(professionalInfo.pdfFileUri, MIME_TYPE_PDF)
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }

        if (professionalInfo.pdfFileUri != null && professionalInfo.pdfFileUri.path?.isNotBlank() == true) {
            Box(
                Modifier
                    .clickable { context.startActivity(intent) }
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.025F),
                        shape = mRoundedCornerMD
                    )
                    .padding(12.dp)
            ) {
                Text(
                    "certificate.pdf",
                    textDecoration = TextDecoration.Underline
                )
            }
        }

        MyButton(label = "Upload certificate PDF") {
            pdfFilePicker.launch(MIME_TYPE_PDF)
        }
    }
}

