package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultStateType
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.MapDsl

@GeneratedDsl
data class BrowserLaunchConfig(
    @DefaultState(type = DefaultStateType.TRUE) val headless: Boolean = true,
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val channel: String = "",
    @ListDsl val args: List<String> = emptyList(),
    @DefaultState(type = DefaultStateType.FALSE) val chromiumSandbox: Boolean = false,
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val downloadsPath: String = "",
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val executablePath: String = "",
    @MapDsl val env: Map<String, String> = emptyMap(),
    @DefaultState(type = DefaultStateType.TRUE) val handleSIGHUP: Boolean = true,
    @DefaultState(type = DefaultStateType.TRUE) val handleSIGINT: Boolean = true,
    @DefaultState(type = DefaultStateType.TRUE) val handleSIGTERM: Boolean = true,
    @DefaultState(type = DefaultStateType.FALSE) val ignoreAllDefaultArgs: Boolean = false,
    @ListDsl val ignoreDefaultArgs: List<String> = emptyList(),
    val proxy: ProxyConfig? = null,
    @DefaultState(type = DefaultStateType.ZERO_DOUBLE) val slowMo: Double = 0.0,
    @DefaultValue("30000.0") val timeout: Double = 30000.0,
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val tracesDir: String = "",
)
