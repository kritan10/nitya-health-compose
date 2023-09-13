package com.kritan.nityahealth.auth.data.models

sealed class AuthEvent {
    object None : AuthEvent()
    object OnLogIn : AuthEvent()
    object OnLogOut : AuthEvent()
    object OnBoarding : AuthEvent()
}