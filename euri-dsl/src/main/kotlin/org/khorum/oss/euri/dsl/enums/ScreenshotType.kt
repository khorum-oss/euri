package org.khorum.oss.euri.dsl.enums

enum class ScreenshotType(val playwrightValue: String) {
    Png("PNG"),
    Jpeg("JPEG");

    fun toPlaywright(): String = playwrightValue.lowercase()
}
