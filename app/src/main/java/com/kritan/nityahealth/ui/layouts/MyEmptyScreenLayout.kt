package com.kritan.nityahealth.ui.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun EmptyScreen(title: String, navigateUp: () -> Unit) {
    MyScaffoldLayout(title = title, navigateUp = navigateUp) {
        Box(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Text("This is $title screen")
        }
    }
}