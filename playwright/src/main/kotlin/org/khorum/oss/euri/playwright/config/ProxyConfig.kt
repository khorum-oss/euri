package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class ProxyConfig(
    val server: String,
    @DefaultValue("\"\"") val bypass: String,
    @DefaultValue("\"\"") val username: String,
    @DefaultValue("\"\"") val password: String,
)
