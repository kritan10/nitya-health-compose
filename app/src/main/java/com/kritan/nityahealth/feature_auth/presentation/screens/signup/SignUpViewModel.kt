package com.kritan.nityahealth.feature_auth.presentation.screens.signup

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.base.utils.Validation
import com.kritan.nityahealth.feature_auth.data.models.AuthState
import com.kritan.nityahealth.feature_auth.data.models.UserRegister
import com.kritan.nityahealth.feature_auth.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) :
    ViewModel() {

    var uiState by mutableStateOf(SignUpState())
        private set

    val isRegisterEnabled by derivedStateOf {
        Validation.validateSignupForm(uiState)
    }

    fun onFirstNameUpdate(firstName: String) {
        uiState = uiState.copy(
            currentFirstName = firstName,
        )
    }

    fun onLastNameUpdate(lastName: String) {
        uiState = uiState.copy(
            currentLastName = lastName,
        )
    }

    fun onPhoneUpdate(phone: String) {
//        val errors = Validation.validatePhone(phone)
        val errors = mutableListOf<String>()
        uiState = uiState.copy(
            currentPhone = phone,
            currentPhoneErrors = errors
        )
    }

    fun onAddressUpdate(address: String) {
        uiState = uiState.copy(
            currentAddress = address,
        )
    }

    fun onEmailUpdate(email: String) {
//        val errors = Validation.validateEmail(email)
        val errors = mutableListOf<String>()

        uiState = uiState.copy(
            currentEmail = email,
            currentEmailErrors = errors,
        )
    }

    fun onPasswordUpdate(password: String) {
//        val errors = Validation.validatePassword(password)
        val errors = mutableListOf<String>()

        uiState = uiState.copy(
            currentPassword = password,
            currentPasswordErrors = errors,
        )
    }

    fun onConfirmPasswordUpdate(password: String) {
//        val errors = Validation.validateConfirmPassword(password, uiState.currentPassword)
        val errors = mutableListOf<String>()

        uiState = uiState.copy(
            currentConfirmPassword = password,
            currentConfirmPasswordErrors = errors,
        )
    }


    fun registerUser(authenticateUser: (AuthState) -> Unit) {
        viewModelScope.launch {
            val token = authRepository.register(
                UserRegister(
                    name = uiState.currentFirstName + " " + uiState.currentLastName,
                    email = uiState.currentEmail,
                    password = uiState.currentPassword,
                    confirmPassword = uiState.currentConfirmPassword,
                    address = uiState.currentAddress,
                    phone = uiState.currentPhone
                )
            )
            token.collect { res ->
                when (res) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> uiState = uiState.copy(isLoading = res.isLoading)
                    is Resource.Success -> res.data?.let { authenticateUser(it) }
                }

            }
        }
    }

}