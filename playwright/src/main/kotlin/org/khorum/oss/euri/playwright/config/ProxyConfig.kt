package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultStateType
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class ProxyConfig(
    val server: String,
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val bypass: String = "",
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val username: String = "",
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val password: String = "",
)
