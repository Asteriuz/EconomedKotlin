package com.asteriuz.economed.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.asteriuz.economed.R

val WorkSans = FontFamily(
    Font(R.font.worksans_variable)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W400,
        fontSize = 64.sp,
        lineHeight = 72.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W400,
        fontSize = 48.sp,
        lineHeight = 56.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W400,
        fontSize = 40.sp,
        lineHeight = 48.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W500,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W500,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W600,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    labelLarge = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = WorkSans,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    )
)
