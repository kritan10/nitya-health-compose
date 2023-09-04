package com.kritan.nityahealth.base.utils

import com.kritan.nityahealth.auth.presentation.screens.signup_email.SignUpState

/**
 * Helper class that contains methods for data validation.
 * Generally used for validating text input fields.
 */
object Validation {
    /**
     * Validates whether the provided string is empty or not
     * @param string the string to be validated if it is empty
     * @return a [MutableList] of appropriate error message(s).
     */
    fun validateEmptyField(string: String): MutableList<String> {
        val errors = mutableListOf<String>()
        if (string.isEmpty()) {
            errors.add("This field cannot be left empty")
        }
        return errors
    }

    /**
     * Validates whether the provided [String] is a valid email address or not.
     * The provided [email] is matched with a standard email [Regex].
     * @param email the string to be validated as an email
     * @return a [MutableList] of appropriate error message(s).
     */
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

    /**
     * Validates whether the provided [String] is a valid password or not.
     * The provided [password] is matched with a standard password [Regex].
     * @param password the string to be validated as a password
     * @return a [MutableList] of appropriate error message(s).
     */
    fun validatePassword(password: String): MutableList<String> {
        val errors = mutableListOf<String>()
        val passwordRegex =
            Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$")
        if (!password.matches(passwordRegex)) {
            errors.add("Weak password")
        }
        return errors
    }

    /**
     * Validates whether the two provided [String]s are equal or not.
     *
     * @param password1 the first password string
     * @param password2 the second password string
     * @return a [MutableList] of appropriate error message(s).
     */
    fun validateConfirmPassword(password1: String, password2: String): MutableList<String> {
        val errors = mutableListOf<String>()
        if (password1 != password2) {
            errors.add("Passwords don't match")
        }
        return errors
    }

    /**
     * Validates whether the provided [String] is a valid Nepali phone number.
     * The provided [phone] is matched with a standard phone number [Regex].
     * @param phone the string to be validated as a phone number
     * @return a [MutableList] of appropriate error message(s).
     */
    fun validatePhone(phone: String): MutableList<String> {
        val errors = mutableListOf<String>()
        val phoneRegex = Regex("^(98|97)\\d{8}\$")
        if (!phone.matches(phoneRegex)) {
            errors.add("Invalid phone number")
        }
        return errors
    }

    /**
     * Validates whether the form can be submitted or not.
     * The provided [SignUpState] form is checked for any possible errors or empty fields.
     * @param form a [SignUpState] object that contains all the form fields displayed in the UI
     * @return `true` if the form is valid and `false` otherwise
     */
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