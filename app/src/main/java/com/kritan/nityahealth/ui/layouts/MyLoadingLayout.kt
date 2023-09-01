package com.kritan.nityahealth.ui.layouts

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable

@Composable
fun MyLoadingLayout(loading: Boolean, content: @Composable () -> Unit) {
    if (loading) {
        CircularProgressIndicator()
    } else {
        content()
    }
}