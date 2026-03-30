package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class ProxyConfig(
    val server: String,
    val bypass: String = "",
    val username: String = "",
    val password: String = "",
)
