package com.technobees.crypfolio.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.technobees.crypfolio.ui.theme.Color as ThreamColor;

private val DarkColorPalette = darkColorScheme(
    primary = ThreamColor.Green80,
    onPrimary = ThreamColor.Green20,
    primaryContainer = ThreamColor.Green30,
    onPrimaryContainer = ThreamColor.Green90,
    inversePrimary = ThreamColor.Green40,
    secondary = ThreamColor.DarkGreen80,
    onSecondary = ThreamColor.DarkGreen20,
    secondaryContainer = ThreamColor.DarkGreen30,
    onSecondaryContainer = ThreamColor.DarkGreen90,
    tertiary = ThreamColor.Violet80,
    onTertiary = ThreamColor.Violet20,
    tertiaryContainer = ThreamColor.Violet30,
    onTertiaryContainer = ThreamColor.Violet90,
    error = ThreamColor.Red80,
    onError = ThreamColor.Red20,
    errorContainer = ThreamColor.Red30,
    onErrorContainer = ThreamColor.Red90,
    background = ThreamColor.Grey10,
    onBackground = ThreamColor.Grey90,
    surface = ThreamColor.GreenGrey30,
    onSurface = ThreamColor.GreenGrey80,
    inverseSurface = ThreamColor.Grey90,
    inverseOnSurface = ThreamColor.Grey10,
    surfaceVariant = ThreamColor.GreenGrey30,
    onSurfaceVariant = ThreamColor.GreenGrey80,
    outline = ThreamColor.GreenGrey80
)

private val LightColorPalette = lightColorScheme(
    primary = ThreamColor.Green40,
    onPrimary = Color.White,
    primaryContainer = ThreamColor.Green90,
    onPrimaryContainer = ThreamColor.Green10,
    inversePrimary = ThreamColor.Green80,
    secondary = ThreamColor.DarkGreen40,
    onSecondary = Color.White,
    secondaryContainer = ThreamColor.DarkGreen90,
    onSecondaryContainer = ThreamColor.DarkGreen10,
    tertiary = ThreamColor.Violet40,
    onTertiary = Color.White,
    tertiaryContainer = ThreamColor.Violet90,
    onTertiaryContainer = ThreamColor.Violet10,
    error = ThreamColor.Red40,
    onError = Color.White,
    errorContainer = ThreamColor.Red90,
    onErrorContainer = ThreamColor.Red10,
    background = ThreamColor.Grey99,
    onBackground = ThreamColor.Grey10,
    surface = ThreamColor.GreenGrey90,
    onSurface = ThreamColor.GreenGrey30,
    inverseSurface = ThreamColor.Grey20,
    inverseOnSurface = ThreamColor.Grey95,
    surfaceVariant = ThreamColor.GreenGrey90,
    onSurfaceVariant = ThreamColor.GreenGrey30,
    outline = ThreamColor.GreenGrey50
)

@Composable
fun CrypfolioTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    //dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val useDynamicColors = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
        useDynamicColors && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        useDynamicColors && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}