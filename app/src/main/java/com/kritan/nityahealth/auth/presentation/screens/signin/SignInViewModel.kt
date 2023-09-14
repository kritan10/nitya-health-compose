package com.kritan.nityahealth.auth.presentation.screens.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritan.nityahealth.auth.AppAuth
import com.kritan.nityahealth.auth.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val appAuth: AppAuth,
    private val authRepository: AuthRepository,
) : ViewModel() {

    val isUserBoarded = appAuth.authState.value.isOnboard

    fun boardInUser() {
        viewModelScope.launch {
            appAuth.boardInUser()
        }
    }

//    fun loginWithFacebook() {
//        viewModelScope.launch {
//            val activity = applicationContext
//            val response = authRepository.loginWithFacebook(applicationContext as Activity)
//            when (response) {
//                is Resource.Error -> Unit
//                is Resource.Loading -> Unit
//                is Resource.Success -> appAuth.authenticateUser(response.data!!)
//            }
//        }
//    }
}