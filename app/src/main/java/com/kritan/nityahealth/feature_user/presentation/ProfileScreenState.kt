package com.kritan.nityahealth.feature_user.presentation

import com.kritan.nityahealth.auth.data.models.AuthSource
import com.kritan.nityahealth.feature_user.data.models.UserData

data class ProfileScreenState(
    val isLoading: Boolean = false,
    val userData: UserData = UserData.defaultUser,
    val authSource: AuthSource

)