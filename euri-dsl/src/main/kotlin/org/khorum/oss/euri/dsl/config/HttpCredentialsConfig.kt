package org.khorum.oss.euri.dsl.config

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyString
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse

@GeneratedDsl
data class HttpCredentialsConfig(
    val username: String,
    val password: String,
    @DefaultEmptyString val origin: String = "",
    @DefaultFalse val sendImmediately: Boolean = false,
)
