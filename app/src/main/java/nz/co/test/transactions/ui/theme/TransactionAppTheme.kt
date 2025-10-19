package nz.co.test.transactions.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TransactionAppTheme(content: @Composable () -> Unit) {

    CompositionLocalProvider(
        LocalAppTypography provides AppBaseTypography,
    ) {
        MaterialTheme(
            content = content,
            typography = Typography()
        )
    }
}

// Helper to access theme
object AppTheme {
    // TODO I wanted to include colors and spacing in the theme but have to skip due to time constraints
    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current
}

// Typography
val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        smallText = TextStyle(),
        mediumText = TextStyle(),
        largeText = TextStyle(),
        xLargeText = TextStyle(),
    )
}

data class AppTypography(
    val smallText: TextStyle,
    val mediumText: TextStyle,
    val largeText: TextStyle,
    val xLargeText: TextStyle,
)

private val AppBaseTypography = AppTypography(
    smallText = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
    ),
    mediumText = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    largeText = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    ),
    xLargeText = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    )
)