package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.DefaultValue
import org.khorum.oss.konstellation.metaDsl.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.ListDsl
import org.khorum.oss.konstellation.metaDsl.MapDsl

@GeneratedDsl
data class BrowserLaunchConfig(
    @DefaultValue("true") val headless: Boolean,
    @DefaultValue("\"\"") val channel: String,
    @ListDsl val args: List<String>,
    @DefaultValue("false") val chromiumSandbox: Boolean,
    @DefaultValue("\"\"") val downloadsPath: String,
    @DefaultValue("\"\"") val executablePath: String,
    @MapDsl val env: Map<String, String>,
    @DefaultValue("true") val handleSIGHUP: Boolean,
    @DefaultValue("true") val handleSIGINT: Boolean,
    @DefaultValue("true") val handleSIGTERM: Boolean,
    @DefaultValue("false") val ignoreAllDefaultArgs: Boolean,
    @ListDsl val ignoreDefaultArgs: List<String>,
    val proxy: ProxyConfig?,
    @DefaultValue("0.0") val slowMo: Double,
    @DefaultValue("30000.0") val timeout: Double,
    @DefaultValue("\"\"") val tracesDir: String,
)
