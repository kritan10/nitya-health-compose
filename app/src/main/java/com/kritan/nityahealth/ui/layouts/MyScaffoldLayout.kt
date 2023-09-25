package com.kritan.nityahealth.ui.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.ui.components.MyTopAppBar
import com.kritan.nityahealth.ui.constants.MyPadding

@Composable
fun MyScaffoldLayout(
    modifier: Modifier = Modifier,
    title: String,
    navigateUp: () -> Unit,
    clipTopWithContent: Boolean = false,
    requireScrolling: Boolean = false,
    padding: PaddingValues = MyPadding.Default,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    actions: @Composable() (RowScope.() -> Unit) = {},
    content: @Composable() (ColumnScope.() -> Unit),
) {
    Scaffold(topBar = {
        MyTopAppBar(title = title, navigateUp = navigateUp, actions = actions)
    }) { scaffoldPv ->
        val mPaddingValues = calculatePadding(
            clipTop = clipTopWithContent,
            scaffoldPadding = scaffoldPv,
            defaultPadding = padding
        )
        Column(
            modifier = modifier
                .padding(mPaddingValues)
                .then(
                    if (requireScrolling) Modifier.verticalScroll(
                        rememberScrollState()
                    ) else Modifier
                ),
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement
        ) {
            this.content()
        }
    }
}

private fun calculatePadding(
    clipTop: Boolean,
    scaffoldPadding: PaddingValues,
    defaultPadding: PaddingValues
): PaddingValues {
    val layoutDirection = LayoutDirection.Ltr

    val top =
        if (!clipTop) defaultPadding.calculateTopPadding() + scaffoldPadding.calculateTopPadding() else 12.dp

    val bottom =
        scaffoldPadding.calculateBottomPadding() + defaultPadding.calculateBottomPadding()

    val start = scaffoldPadding.calculateStartPadding(layoutDirection) +
            defaultPadding.calculateStartPadding(layoutDirection)

    val end = scaffoldPadding.calculateEndPadding(layoutDirection) +
            defaultPadding.calculateEndPadding(layoutDirection)

    return PaddingValues(
        top = top,
        bottom = bottom,
        start = start,
        end = end
    )
}

