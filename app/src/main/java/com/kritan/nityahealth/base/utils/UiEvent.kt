package com.kritan.nityahealth.base.utils

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
    object NavigateToDashboard : UiEvent()
}
