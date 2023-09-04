package com.kritan.nityahealth.auth.presentation.screens.signin

import androidx.lifecycle.ViewModel
import com.kritan.nityahealth.auth.AppAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(val appAuth: AppAuth) : ViewModel() {
}