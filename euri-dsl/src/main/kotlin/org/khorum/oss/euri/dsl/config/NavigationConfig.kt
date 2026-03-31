package org.khorum.oss.euri.dsl.config

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyString

@GeneratedDsl
data class NavigationConfig(
    val url: String,
    @DefaultValue("LOAD") val waitUntil: String = "LOAD",
    @DefaultValue("30000.0") val timeout: Double = 30000.0,
    @DefaultEmptyString val referer: String = "",
)
