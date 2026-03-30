package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultStateType
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class HttpCredentialsConfig(
    val username: String,
    val password: String,
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val origin: String = "",
    @DefaultState(type = DefaultStateType.FALSE) val sendImmediately: Boolean = false,
)
