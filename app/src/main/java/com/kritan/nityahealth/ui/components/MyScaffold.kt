package com.kritan.nityahealth.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.commons.components.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold(
    title: String,
    navigateUp: () -> Unit,
    bottomPadding: Int = 0,
    horizontalPadding: Int = 20,
    isFlush: Boolean = false,
    content: @Composable () -> Unit
) {
    Scaffold(topBar = {
        MyTopAppBar(title = title, navigateUp = navigateUp)
    }) { pv ->
        val topPadding = if (!isFlush) pv.calculateTopPadding() else pv.calculateTopPadding() - 8.dp
        Column(
            Modifier.padding(
                top = topPadding,
                bottom = bottomPadding.dp,
                start = horizontalPadding.dp,
                end = horizontalPadding.dp
            )
        ) {
            content()
        }
    }
}