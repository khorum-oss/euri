package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultStateType
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class NavigationConfig(
    val url: String,
    @DefaultValue("LOAD") val waitUntil: String = "LOAD",
    @DefaultValue("30000.0") val timeout: Double = 30000.0,
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val referer: String = "",
)
