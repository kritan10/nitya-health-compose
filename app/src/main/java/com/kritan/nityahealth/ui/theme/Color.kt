package com.kritan.nityahealth.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val mBlue = Color(0xFF16C2D5)
val altBlue = Color(0x3316C2D5)
val mWhite = Color(0xFFFFFFFF)
val mBlack = Color(0xFF001100)
val mListBgColor = Color(0x050BAADC)
val mDividerColor = Color(0xFFECEAEA)

val myDarkColorScheme = darkColorScheme(
    primary = mBlue,
    onPrimary = mWhite,
    background = mWhite,
    onBackground = mBlack,
    surface = mBlue,
    onSurface = mWhite,
    onSurfaceVariant = mWhite
)

val myLightColorScheme = lightColorScheme(
    primary = mBlue,
    onPrimary = mWhite,
    background = mWhite,
    onBackground = mBlack,
    surface = mBlue,
    onSurface = mWhite,
    onSurfaceVariant = mWhite
)