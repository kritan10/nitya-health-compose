package com.kritan.nityahealth.feature_auth.presentation.screens.signin_email

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.base.utils.Validation
import com.kritan.nityahealth.feature_auth.data.models.UserLogin
import com.kritan.nityahealth.feature_auth.data.models.UserRegister
import com.kritan.nityahealth.feature_auth.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInEmailViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    var uiState by mutableStateOf(SignInEmailState())
        private set

    fun onEmailUpdate(email: String) {
        val errors = Validation.validateEmail(email)
        uiState = uiState.copy(
            currentEmail = email,
            currentEmailErrors = errors,
        )
    }


    fun onPasswordUpdate(password: String) {
        val errors = Validation.validatePassword(password)
        uiState = uiState.copy(
            currentPassword = password,
            currentPasswordErrors = errors,
        )
    }

    fun loginUser() {
        viewModelScope.launch {
            authRepository.login(UserLogin(email = "", password = "")).collect { response ->
                when (response) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> uiState = uiState.copy(isLoading = response.isLoading)
                    is Resource.Success -> {
                        uiState = uiState.copy(

                        )
                    }
                }
            }
        }
    }

}