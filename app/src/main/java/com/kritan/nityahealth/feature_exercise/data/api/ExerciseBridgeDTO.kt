package com.kritan.nityahealth.feature_exercise.data.api

import com.google.gson.annotations.SerializedName
import com.kritan.nityahealth.feature_exercise.data.models.ExerciseBridge

data class ExerciseBridgeDTO(
    @SerializedName("package")
    var mPackage: List<ExerciseBridge>

)
