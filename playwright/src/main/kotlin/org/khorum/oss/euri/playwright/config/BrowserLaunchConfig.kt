package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultEmptyList
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultEmptyMap
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultEmptyString
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultNull
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.MapDsl

@GeneratedDsl
@DefaultState
data class BrowserLaunchConfig(
    @DefaultValue("true") val headless: Boolean = true,
    @DefaultEmptyString val channel: String = "",
    @DefaultEmptyList @ListDsl val args: List<String> = emptyList(),
    @DefaultValue("false") val chromiumSandbox: Boolean = false,
    @DefaultEmptyString val downloadsPath: String = "",
    @DefaultEmptyString val executablePath: String = "",
    @DefaultEmptyMap @MapDsl val env: Map<String, String> = emptyMap(),
    @DefaultValue("true") val handleSIGHUP: Boolean = true,
    @DefaultValue("true") val handleSIGINT: Boolean = true,
    @DefaultValue("true") val handleSIGTERM: Boolean = true,
    @DefaultValue("false") val ignoreAllDefaultArgs: Boolean = false,
    @DefaultEmptyList @ListDsl val ignoreDefaultArgs: List<String> = emptyList(),
    @DefaultNull val proxy: ProxyConfig? = null,
    @DefaultValue("0.0") val slowMo: Double = 0.0,
    @DefaultValue("30000.0") val timeout: Double = 30000.0,
    @DefaultEmptyString val tracesDir: String = "",
)
