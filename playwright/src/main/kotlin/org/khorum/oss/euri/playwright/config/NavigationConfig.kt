package org.khorum.oss.euri.playwright.config

import org.khorum.oss.euri.playwright.enums.WaitUntil
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultEnum
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyString

@GeneratedDsl
data class NavigationConfig(
    val url: String,
    @DefaultEnum(value = "Load", packageName = "org.khorum.oss.euri.playwright.enums", className = "WaitUntil") val waitUntil: WaitUntil = WaitUntil.Load,
    @DefaultValue("30000.0") val timeout: Double = 30000.0,
    @DefaultEmptyString val referer: String = "",
)
