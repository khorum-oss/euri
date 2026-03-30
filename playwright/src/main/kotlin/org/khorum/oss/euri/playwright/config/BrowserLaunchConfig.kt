package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.MapDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyString
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultTrue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultZeroDouble

@GeneratedDsl
data class BrowserLaunchConfig(
    @DefaultTrue val headless: Boolean = true,
    @DefaultEmptyString val channel: String = "",
    @ListDsl val args: List<String> = emptyList(),
    @DefaultFalse val chromiumSandbox: Boolean = false,
    @DefaultEmptyString val downloadsPath: String = "",
    @DefaultEmptyString val executablePath: String = "",
    @MapDsl val env: Map<String, String> = emptyMap(),
    @DefaultTrue val handleSIGHUP: Boolean = true,
    @DefaultTrue val handleSIGINT: Boolean = true,
    @DefaultTrue val handleSIGTERM: Boolean = true,
    @DefaultFalse val ignoreAllDefaultArgs: Boolean = false,
    @ListDsl val ignoreDefaultArgs: List<String> = emptyList(),
    val proxy: ProxyConfig? = null,
    @DefaultZeroDouble val slowMo: Double = 0.0,
    @DefaultValue("30000.0") val timeout: Double = 30000.0,
    @DefaultEmptyString val tracesDir: String = "",
)
