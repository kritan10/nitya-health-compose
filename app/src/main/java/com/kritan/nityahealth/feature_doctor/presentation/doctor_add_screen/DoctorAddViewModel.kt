package com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.models.DoctorPersonalInfo
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.models.DoctorPersonalInfo.Companion.validateDoctorPersonalInfo
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.models.DoctorProfessionalInfo
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.models.DoctorProfessionalInfo.Companion.validateDoctorProfessionalInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoctorAddViewModel @Inject constructor() : ViewModel() {

    val questionOrder = listOf(
        DoctorQuestion.START,
        DoctorQuestion.PERSONAL_INFO,
        DoctorQuestion.PROFESSIONAL_INFO,
        DoctorQuestion.DETAILS,
        DoctorQuestion.END
    )

    var questionIndex by mutableIntStateOf(0)
        private set

    fun moveToPreviousQuestion(): Boolean {
        if (questionIndex <= 0) {
            return false
        }

        questionIndex--
        return true
    }

    fun moveToNextQuestion() {
        questionIndex++
    }

    fun canMoveToNextQuestion(): Boolean {
        return when (questionOrder[questionIndex]) {
            DoctorQuestion.START -> true

            DoctorQuestion.PERSONAL_INFO -> {
                validateDoctorPersonalInfo(personalInfoResponse)
            }

            DoctorQuestion.PROFESSIONAL_INFO -> {
                validateDoctorProfessionalInfo(professionalInfoResponse)
            }

            DoctorQuestion.DETAILS -> true

            DoctorQuestion.END -> true
        }
    }


    // -- Doctor personal information page : Start -- //
    var personalInfoResponse by mutableStateOf(DoctorPersonalInfo())
        private set

    fun onPersonalInfoResponse(personalInfo: DoctorPersonalInfo) {
        personalInfoResponse = personalInfo
    }
    // -- Doctor personal information page : End -- //


    // -- Doctor professional information page : Start -- //
    var professionalInfoResponse by mutableStateOf(DoctorProfessionalInfo())
        private set

    fun onProfessionalInfoResponse(professionalInfo: DoctorProfessionalInfo) {
        professionalInfoResponse = professionalInfo
    }
    // -- Doctor professional information page : End -- //


//    private val _superheroResponse = mutableStateOf<Superhero?>(null)
//    val superheroResponse: Superhero?
//        get() = _superheroResponse.value

}

enum class DoctorQuestion {
    START, PERSONAL_INFO, PROFESSIONAL_INFO, END, DETAILS
}

