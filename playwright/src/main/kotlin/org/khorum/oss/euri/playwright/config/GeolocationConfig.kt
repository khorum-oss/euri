package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
@DefaultState
data class GeolocationConfig(
    val latitude: Double,
    val longitude: Double,
    val accuracy: Double = 0.0,
)
