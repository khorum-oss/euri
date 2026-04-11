package org.khorum.oss.euri.dsl.enums

import com.microsoft.playwright.options.ScreenshotType as PlaywrightScreenshotType

enum class ScreenshotType(val playwrightValue: String) {
    Png("PNG"),
    Jpeg("JPEG");

    fun toPlaywright(): PlaywrightScreenshotType = PlaywrightScreenshotType.valueOf(playwrightValue)
}
