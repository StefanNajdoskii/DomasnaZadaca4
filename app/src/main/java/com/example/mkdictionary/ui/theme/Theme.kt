package com.example.mkdictionary.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MKColorScheme = lightColorScheme(
    primary          = RedMK,
    onPrimary        = Color.White,
    primaryContainer = LightRed,
    secondary        = DarkRed,
    tertiary         = YellowMK,
    background       = SurfaceWarm,
    surface          = Color.White,
)

@Composable
fun MKDictionaryTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MKColorScheme,
        typography  = Typography,
        content     = content
    )
}