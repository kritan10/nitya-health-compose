package com.kritan.nityahealth.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val myTypography = Typography(
    //body
    bodyLarge = TextStyle(
        fontFamily = comfortaaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 17.84.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = comfortaaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 15.61.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = comfortaaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 13.38.sp,
        letterSpacing = 0.5.sp
    ),

    //button
    labelLarge = TextStyle(
        color = Color.White,
        fontFamily = comfortaaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 15.61.sp,
        letterSpacing = 0.4.sp
    ),

    //smaller text for buttons
    labelMedium = TextStyle(
        fontFamily = comfortaaFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 13.38.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = comfortaaFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    //title
    titleLarge = TextStyle(
        fontFamily = comfortaaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 20.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = comfortaaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ),

    //headline-for scaffold content
    headlineMedium = TextStyle(
        fontFamily = comfortaaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    )


    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)