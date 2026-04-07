package org.khorum.oss.euri.playwright.enums

import com.microsoft.playwright.options.ColorScheme

enum class BrowserColorScheme(val playwrightValue: String) {
    Dark("DARK"),
    Light("LIGHT"),
    NoPreference("NO_PREFERENCE"),
    Null("NULL");

    fun toPlaywright(): ColorScheme = ColorScheme.valueOf(playwrightValue)
}
