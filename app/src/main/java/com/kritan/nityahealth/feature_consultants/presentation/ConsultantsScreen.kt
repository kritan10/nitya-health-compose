package com.kritan.nityahealth.feature_consultants.presentation

import androidx.compose.runtime.Composable
import com.kritan.nityahealth.ui.layouts.MyGridLayout
import com.kritan.nityahealth.ui.layouts.MyScaffoldLayout

@Composable
fun ConsultantsScreen(navigateUp: () -> Unit) {
    MyScaffoldLayout(title = "Consultants", navigateUp = navigateUp) {
        MyGridLayout()
    }
}