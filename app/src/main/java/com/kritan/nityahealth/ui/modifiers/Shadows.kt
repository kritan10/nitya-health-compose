package com.kritan.nityahealth.ui.modifiers

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.ui.theme.mShadowColor

@Composable
fun mShadow(
    elevation: Int = 10,
    shape: Shape = RectangleShape,
    spotColor: Color = mShadowColor,
    ambientColor: Color = mShadowColor,
): Modifier {
    return Modifier
        .shadow(
            shape = shape,
            elevation = elevation.dp,
            spotColor = spotColor,
            ambientColor = ambientColor
        )
        .background(color = MaterialTheme.colorScheme.background, shape = shape)
}
