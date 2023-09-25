package com.kritan.nityahealth.ui.constants

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

object MyPadding {
    val Default = PaddingValues(
        top = 16.dp,
        bottom = 0.dp,
        start = 20.dp,
        end = 20.dp
    )
    val HorizontalOnly = PaddingValues(horizontal = 20.dp)

    val None = PaddingValues(0.dp)

    val TopOnly = PaddingValues(top = 16.dp)
}