package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.MapDsl

@GeneratedDsl
data class BrowserLaunchConfig(
    val headless: Boolean = true,
    val channel: String = "",
    @ListDsl val args: List<String> = emptyList(),
    val chromiumSandbox: Boolean = false,
    val downloadsPath: String = "",
    val executablePath: String = "",
    @MapDsl val env: Map<String, String> = emptyMap(),
    val handleSIGHUP: Boolean = true,
    val handleSIGINT: Boolean = true,
    val handleSIGTERM: Boolean = true,
    val ignoreAllDefaultArgs: Boolean = false,
    @ListDsl val ignoreDefaultArgs: List<String> = emptyList(),
    val proxy: ProxyConfig? = null,
    val slowMo: Double = 0.0,
    val timeout: Double = 30000.0,
    val tracesDir: String = "",
)
