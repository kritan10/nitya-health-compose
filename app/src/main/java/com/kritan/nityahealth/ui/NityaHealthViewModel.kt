package com.kritan.nityahealth.ui

import androidx.lifecycle.ViewModel
import com.kritan.nityahealth.auth.AppAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NityaHealthViewModel @Inject constructor(appAuth: AppAuth) : ViewModel() {
    val authState = appAuth.authState
}