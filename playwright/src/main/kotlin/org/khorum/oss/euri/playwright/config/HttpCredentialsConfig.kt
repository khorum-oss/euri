package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class HttpCredentialsConfig(
    val username: String,
    val password: String,
    @DefaultValue("\"\"") val origin: String,
    @DefaultValue("false") val sendImmediately: Boolean,
)
