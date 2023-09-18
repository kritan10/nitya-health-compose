package com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.utils

fun Int?.blankWhenNull(): String {
    if(this == null) return ""
    return this.toString()
}