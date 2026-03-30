package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultEmptyList
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultEmptyString
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultNull
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl

@GeneratedDsl
@DefaultState
data class ScreenshotConfig(
    @DefaultEmptyString val path: String = "",
    @DefaultValue("PNG") val type: String = "PNG",
    @DefaultValue("-1") val quality: Int = -1,
    @DefaultValue("false") val fullPage: Boolean = false,
    @DefaultNull val clip: ClipConfig? = null,
    @DefaultValue("false") val omitBackground: Boolean = false,
    @DefaultEmptyString val style: String = "",
    @DefaultEmptyList @ListDsl val maskSelectors: List<String> = emptyList(),
)
