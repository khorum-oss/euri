package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl

@GeneratedDsl
@DefaultState
data class ScreenshotConfig(
    val path: String = "",
    val type: String = "PNG",
    val quality: Int = -1,
    val fullPage: Boolean = false,
    val clip: ClipConfig? = null,
    val omitBackground: Boolean = false,
    val style: String = "",
    @ListDsl val maskSelectors: List<String> = emptyList(),
)
