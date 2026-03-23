package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.DefaultValue
import org.khorum.oss.konstellation.metaDsl.GeneratedDsl

@GeneratedDsl
data class NavigationConfig(
    val url: String,
    @DefaultValue("\"LOAD\"") val waitUntil: String,
    @DefaultValue("30000.0") val timeout: Double,
    @DefaultValue("\"\"") val referer: String,
)
