package com.kritan.nityahealth.auth.presentation.screens.signup_email

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kritan.nityahealth.auth.AppAuth
import com.kritan.nityahealth.auth.data.models.AuthUserData
import com.kritan.nityahealth.auth.data.repository.AuthRepository
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.base.utils.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val appAuth: AppAuth
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
        val errors = mutableListOf<String>()
        errors.addAll(Validation.validateEmptyField(phone))
        errors.addAll(Validation.validatePhone(phone))
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
        val errors = mutableListOf<String>()
        errors.addAll(Validation.validateEmptyField(email))
        errors.addAll(Validation.validateEmail(email))

        uiState = uiState.copy(
            currentEmail = email,
            currentEmailErrors = errors,
        )
    }

    fun onPasswordUpdate(password: String) {
        val errors = Validation.validatePassword(password)
//        val errors = mutableListOf<String>()

        uiState = uiState.copy(
            currentPassword = password,
            currentPasswordErrors = errors,
        )
    }

    fun onConfirmPasswordUpdate(cPassword: String) {
        val errors = Validation.validateConfirmPassword(cPassword, uiState.currentPassword)
//        val errors = mutableListOf<String>()

        uiState = uiState.copy(
            currentConfirmPassword = cPassword,
            currentConfirmPasswordErrors = errors,
        )
    }


    fun registerUser() {
        viewModelScope.launch {
            authRepository.registerWithEmail(
                AuthUserData.UserRegister(
                    name = uiState.currentFirstName + " " + uiState.currentLastName,
                    email = uiState.currentEmail,
                    password = uiState.currentPassword,
                    confirmPassword = uiState.currentConfirmPassword,
                    address = uiState.currentAddress,
                    phone = uiState.currentPhone
                )
            ).collect { res ->
                when (res) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> uiState = uiState.copy(isLoading = res.isLoading)
                    is Resource.Success -> res.data?.let { appAuth.authenticateUser(it) }
                }
            }
        }
    }

}