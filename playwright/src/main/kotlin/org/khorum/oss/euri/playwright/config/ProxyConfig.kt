package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultEmptyString
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
@DefaultState
data class ProxyConfig(
    val server: String,
    @DefaultEmptyString val bypass: String = "",
    @DefaultEmptyString val username: String = "",
    @DefaultEmptyString val password: String = "",
)
