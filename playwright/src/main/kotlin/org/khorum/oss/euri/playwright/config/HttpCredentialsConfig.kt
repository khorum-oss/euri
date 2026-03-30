package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultEmptyString
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
@DefaultState
data class HttpCredentialsConfig(
    val username: String,
    val password: String,
    @DefaultEmptyString val origin: String = "",
    @DefaultValue("false") val sendImmediately: Boolean = false,
)
