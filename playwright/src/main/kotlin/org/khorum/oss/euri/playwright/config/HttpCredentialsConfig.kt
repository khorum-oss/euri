package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class HttpCredentialsConfig(
    val username: String,
    val password: String,
    val origin: String = "",
    val sendImmediately: Boolean = false,
)
