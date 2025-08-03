import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily

val fontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = com.example.studdybuddy.R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Poppins")


val poppinsFont = GoogleFont("Poppins")

val fontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = fontProvider),
)

// Font family definition
val Poppins = FontFamily(
    Font(poppinsFont, fontProvider, FontWeight.Normal),
    Font(poppinsFont, fontProvider, FontWeight.Medium),
    Font(poppinsFont, fontProvider, FontWeight.Bold, style = FontStyle.Normal)
)

private val defaultTypography = Typography()

val AppTypography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = Poppins),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = Poppins),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = Poppins),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = Poppins),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = Poppins),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = Poppins),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = Poppins),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = Poppins),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = Poppins),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = Poppins),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = Poppins),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = Poppins),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = Poppins),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = Poppins),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = Poppins)
)