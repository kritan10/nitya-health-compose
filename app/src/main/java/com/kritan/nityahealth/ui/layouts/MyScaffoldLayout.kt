package com.kritan.nityahealth.ui.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.ui.components.MyTopAppBar

@Composable
fun MyScaffoldLayout(
    title: String,
    navigateUp: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    isFlush: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(topBar = {
        MyTopAppBar(title = title, navigateUp = navigateUp, actions = actions)
    }) { pv ->
        val topPadding = if (!isFlush) pv.calculateTopPadding() else pv.calculateTopPadding() - 8.dp
        val bottomPadding = pv.calculateBottomPadding()
        Column {
            Spacer(Modifier.height(topPadding))
            this.content()
            Spacer(Modifier.height(bottomPadding))
        }
    }
}