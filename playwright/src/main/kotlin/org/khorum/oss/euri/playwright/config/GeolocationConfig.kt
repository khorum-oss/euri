package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.DefaultValue
import org.khorum.oss.konstellation.metaDsl.GeneratedDsl

@GeneratedDsl
data class GeolocationConfig(
    val latitude: Double,
    val longitude: Double,
    @DefaultValue("0.0") val accuracy: Double,
)
