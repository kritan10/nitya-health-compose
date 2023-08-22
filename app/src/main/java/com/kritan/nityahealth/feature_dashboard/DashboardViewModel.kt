package com.kritan.nityahealth.feature_dashboard

import androidx.lifecycle.ViewModel
import com.kritan.nityahealth.R
import com.kritan.nityahealth.ui.NityaHealthDestinations
import com.kritan.nityahealth.ui.layouts.MyGridItem

sealed class ViewState {
    object IsFirstTime : ViewState()
    object IsLoggedIn : ViewState()
    object IsNotLoggedIn : ViewState()
    object IsLoading : ViewState()
}

class DashboardViewModel() : ViewModel() {


}




