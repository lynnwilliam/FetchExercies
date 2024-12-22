package com.fetch.lynnwilliam.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

data class CustomColors(
    val oddRowColor: Color,
    val evenRowColor: Color,
    val textColor: Color
)

// Define light and dark theme colors specifically
val LightCustomColors = CustomColors(
    oddRowColor = Color(0xFF2196F3),
    evenRowColor = Color(0xFF00BCD4),
    textColor =  Color(0xFFFFFFFF),
)

val DarkCustomColors = CustomColors(
    oddRowColor = Color(0xFFD1C4E9),
    evenRowColor = Color(0xFFFFECB3) ,
    textColor =  Color(0xFF000000),
)

val LocalCustomColors = staticCompositionLocalOf { LightCustomColors } // Default to light