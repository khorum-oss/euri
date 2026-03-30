package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyString

@GeneratedDsl
data class ProxyConfig(
    val server: String,
    @DefaultEmptyString val bypass: String = "",
    @DefaultEmptyString val username: String = "",
    @DefaultEmptyString val password: String = "",
)
