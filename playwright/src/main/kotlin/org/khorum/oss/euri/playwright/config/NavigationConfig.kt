package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class NavigationConfig(
    val url: String,
    val waitUntil: String = "LOAD",
    val timeout: Double = 30000.0,
    val referer: String = "",
)
