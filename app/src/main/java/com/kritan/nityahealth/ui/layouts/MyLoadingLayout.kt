package com.kritan.nityahealth.ui.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MyLoadingLayout(loading: Boolean, content: @Composable () -> Unit) {
    if (loading) {
        Box(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)) {
            CircularProgressIndicator()
        }
    } else {
        content()
    }
}