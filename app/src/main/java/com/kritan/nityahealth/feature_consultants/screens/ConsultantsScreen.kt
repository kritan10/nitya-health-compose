package com.kritan.nityahealth.feature_consultants.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kritan.nityahealth.commons.components.MyTopAppBar
import com.kritan.nityahealth.ui.layouts.MyGridLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultantsScreen(navigateUp: () -> Unit) {
    Scaffold(
        topBar = { MyTopAppBar(title = "Consultants", navigateUp = navigateUp) }
    )
    { pv ->
        MyGridLayout(modifier = Modifier.padding(pv))
    }

}