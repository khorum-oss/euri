package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.DefaultValue
import org.khorum.oss.konstellation.metaDsl.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.ListDsl

@GeneratedDsl
data class ScreenshotConfig(
    @DefaultValue("\"\"") val path: String,
    @DefaultValue("\"PNG\"") val type: String,
    @DefaultValue("-1") val quality: Int,
    @DefaultValue("false") val fullPage: Boolean,
    val clip: ClipConfig?,
    @DefaultValue("false") val omitBackground: Boolean,
    @DefaultValue("\"\"") val style: String,
    @ListDsl val maskSelectors: List<String>,
)
