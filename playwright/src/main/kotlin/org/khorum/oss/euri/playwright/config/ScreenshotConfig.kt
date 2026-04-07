package org.khorum.oss.euri.playwright.config

import org.khorum.oss.euri.playwright.enums.ScreenshotType
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultEnum
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyList
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyString
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse

@GeneratedDsl
data class ScreenshotConfig(
    @DefaultEmptyString val path: String = "",
    @DefaultEnum(value = "Png", packageName = "org.khorum.oss.euri.playwright.enums", className = "ScreenshotType") val type: ScreenshotType = ScreenshotType.Png,
    @DefaultValue("-1") val quality: Int = -1,
    @DefaultFalse val fullPage: Boolean = false,
    val clip: ClipConfig? = null,
    @DefaultFalse val omitBackground: Boolean = false,
    @DefaultEmptyString val style: String = "",
    @DefaultEmptyList @ListDsl val maskSelectors: List<String> = emptyList(),
)
