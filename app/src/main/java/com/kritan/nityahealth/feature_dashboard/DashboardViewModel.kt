package com.kritan.nityahealth.feature_dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

sealed class ViewState {
    object IsFirstTime : ViewState()
    object IsLoggedIn : ViewState()
    object IsNotLoggedIn : ViewState()
    object IsLoading : ViewState()
}

class DashboardViewModel : ViewModel() {
    private var isAuth = MutableStateFlow(false)

    val viewState = isAuth.map {
        if (it) ViewState.IsLoggedIn else ViewState.IsNotLoggedIn
    }

    fun onAuth() {
        viewModelScope.launch {
            isAuth.value = true
        }
    }


}