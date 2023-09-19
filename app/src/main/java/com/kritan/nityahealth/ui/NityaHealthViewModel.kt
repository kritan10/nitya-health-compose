package com.kritan.nityahealth.ui

import androidx.lifecycle.ViewModel
import com.kritan.nityahealth.auth.AppAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class NityaHealthViewModel @Inject constructor(
    appAuth: AppAuth,
//    @ApplicationContext applicationContext: Context,
) : ViewModel() {
    val authState = appAuth.authState

    var isLoading = MutableStateFlow(true)

//    init {
//        val apiKey = ""
//
//        // Setup Places Client
//        if (!Places.isInitialized()) {
//            Places.initialize(applicationContext, apiKey)
//        }
//    }

    fun closeSplashScreen() {
        isLoading.value = false
    }
}