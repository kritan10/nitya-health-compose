package com.kritan.nityahealth.base.utils

import com.kritan.nityahealth.feature_auth.presentation.screens.signup.SignUpState

object Validation {
    fun validateEmail(email: String): MutableList<String> {
        val errors = mutableListOf<String>()
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
        return if (email.length > 6 && email.matches(emailRegex)) {
            errors
        } else {
            errors.add("Invalid email address")
            errors
        }

    }

    fun validatePassword(password: String): MutableList<String> {
        val errors = mutableListOf<String>()
        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$\n")
        if (!password.matches(passwordRegex)) {
            errors.add("Weak password")
        }
        return errors
    }

    fun validateConfirmPassword(password1: String, password2: String): MutableList<String> {
        val errors = mutableListOf<String>()
        if (password1 == password2) {
            errors.add("Passwords don't match")
        }
        return errors
    }

    fun validatePhone(phone: String): MutableList<String> {
        val errors = mutableListOf<String>()
        val phoneRegex = Regex("^(98|97)\\d{8}\$\n")
        if (!phone.matches(phoneRegex)) {
            errors.add("Invalid phone number")
        }
        return errors
    }

    fun validateSignupForm(form: SignUpState): Boolean {
        return form.currentFirstName.isNotEmpty()
                && form.currentLastName.isNotEmpty()
                && form.currentEmail.isNotEmpty()
                && form.currentPassword.isNotEmpty()
                && form.currentPhone.isNotEmpty()
                && form.currentAddress.isNotEmpty()
                && form.currentPhoneErrors.isEmpty()
                && form.currentEmailErrors.isEmpty()
                && form.currentPasswordErrors.isEmpty()
                && form.currentConfirmPasswordErrors.isEmpty()
    }
}