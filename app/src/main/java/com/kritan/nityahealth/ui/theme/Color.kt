package com.kritan.nityahealth.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val lightPrimary = Color(0xFF16C2D5)
val darkPrimary = Color(0xFF0F7E8A)

val mWhite = Color(0xFFF5F5F5)
val mBlack = Color(0xFF111111)

val mListBgColor = Color(0x050BAADC)
val mShadowColor = Color(0xAA000000)

val darkBackground = Color(0xFF1A1A1A)

val lightError = Color(0xFFE57373)
val darkError = Color(0xFFD32F2F)

val myDarkColorScheme = darkColorScheme(
    primary = darkPrimary,
    onPrimary = mWhite,
    background = darkBackground,
    onBackground = mWhite,
    surface = darkPrimary,
    onSurface = mWhite,
    onSurfaceVariant = mWhite,
    onError = darkError
)

val myLightColorScheme = lightColorScheme(
    primary = lightPrimary,
    onPrimary = mWhite,
    background = mWhite,
    onBackground = mBlack,
    surface = lightPrimary,
    onSurface = mWhite,
    onSurfaceVariant = mWhite,
    onError = lightError
)