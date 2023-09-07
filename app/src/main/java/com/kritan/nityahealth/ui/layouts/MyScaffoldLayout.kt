package com.kritan.nityahealth.ui.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.ui.components.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldLayout(
    title: String,
    navigateUp: () -> Unit,
    isFlush: Boolean = false,
    content: @Composable () -> Unit
) {
    Scaffold(topBar = {
        MyTopAppBar(title = title, navigateUp = navigateUp)
    }) { pv ->
        val topPadding = if (!isFlush) pv.calculateTopPadding() else pv.calculateTopPadding() - 8.dp
        val bottomPadding = pv.calculateBottomPadding()
        Column {
            Spacer(Modifier.height(topPadding))
            content()
            Spacer(Modifier.height(bottomPadding))
        }
    }
}