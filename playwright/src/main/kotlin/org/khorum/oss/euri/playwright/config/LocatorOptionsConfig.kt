package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultStateType
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class LocatorOptionsConfig(
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val hasText: String = "",
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val hasNotText: String = "",
)
