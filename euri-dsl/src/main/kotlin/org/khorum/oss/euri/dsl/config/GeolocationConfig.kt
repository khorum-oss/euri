package org.khorum.oss.euri.dsl.config

import com.microsoft.playwright.options.Geolocation
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultZeroDouble

@GeneratedDsl
data class GeolocationConfig(
    val latitude: Double,
    val longitude: Double,
    @DefaultZeroDouble val accuracy: Double = 0.0,
) : PlaywrightConfig<Geolocation> {
    override fun toPlaywright(): Geolocation = Geolocation(latitude, longitude).setAccuracy(accuracy)
}
