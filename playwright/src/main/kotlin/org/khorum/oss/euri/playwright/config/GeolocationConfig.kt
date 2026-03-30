package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultStateType
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class GeolocationConfig(
    val latitude: Double,
    val longitude: Double,
    @DefaultState(type = DefaultStateType.ZERO_DOUBLE) val accuracy: Double = 0.0,
)
