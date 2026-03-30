package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultZeroDouble

@GeneratedDsl
data class GeolocationConfig(
    val latitude: Double,
    val longitude: Double,
    @DefaultZeroDouble val accuracy: Double = 0.0,
)
