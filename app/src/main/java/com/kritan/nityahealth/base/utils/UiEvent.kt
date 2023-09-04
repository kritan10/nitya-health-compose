package com.kritan.nityahealth.base.utils

/**
 * `UiEvent` class is a general purpose class that is used to transmit events from view-models to views.
 * It can be best utilized by using it in conjunction with `StateFlow` and `LaunchedEffect`.
 * The events can be emitted from the view-model in a `StateFlow` and
 * can be collected in a `LaunchedEffect` block which updates the UI on every emission.
 *
 * @see androidx.compose.runtime.LaunchedEffect
 * @see kotlinx.coroutines.flow.StateFlow
 */
sealed class UiEvent {
    /**
     * `ShowSnackbar` is a sub-class of `UiEvent`.
     * It is used to signal the view to show a snackbar with [message]
     *
     * @property message the message to be displayed in the snackbar
     */
    data class ShowSnackbar(val message: String) : UiEvent()

    /**
     * `NavigateToDashboard` is a sub-class of `UiEvent`.
     * It is used to signal the view to navigate to the dashboard.
     */
    object NavigateToDashboard : UiEvent()
}
