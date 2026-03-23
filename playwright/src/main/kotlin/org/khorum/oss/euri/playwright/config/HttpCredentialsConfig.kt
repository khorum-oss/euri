package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.DefaultValue
import org.khorum.oss.konstellation.metaDsl.GeneratedDsl

@GeneratedDsl
data class HttpCredentialsConfig(
    val username: String,
    val password: String,
    @DefaultValue("\"\"") val origin: String,
    @DefaultValue("false") val sendImmediately: Boolean,
)
