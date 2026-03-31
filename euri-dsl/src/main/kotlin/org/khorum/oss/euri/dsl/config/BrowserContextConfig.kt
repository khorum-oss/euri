package org.khorum.oss.euri.dsl.config

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.MapDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyList
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyMap
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyString
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultTrue

@GeneratedDsl
data class BrowserContextConfig(
    @DefaultTrue val acceptDownloads: Boolean = true,
    @DefaultEmptyString val baseURL: String = "",
    @DefaultFalse val bypassCSP: Boolean = false,
    @DefaultValue("NO_PREFERENCE") val colorScheme: String = "NO_PREFERENCE",
    @DefaultValue("1.0") val deviceScaleFactor: Double = 1.0,
    @DefaultEmptyMap @MapDsl val extraHTTPHeaders: Map<String, String> = emptyMap(),
    @DefaultFalse val hasTouch: Boolean = false,
    val geolocation: GeolocationConfig? = null,
    val httpCredentials: HttpCredentialsConfig? = null,
    @DefaultFalse val ignoreHTTPSErrors: Boolean = false,
    @DefaultFalse val isMobile: Boolean = false,
    @DefaultTrue val javaScriptEnabled: Boolean = true,
    @DefaultEmptyString val locale: String = "",
    @DefaultFalse val offline: Boolean = false,
    @DefaultEmptyList @ListDsl val permissions: List<String> = emptyList(),
    val proxy: ProxyConfig? = null,
    @DefaultEmptyString val recordHarPath: String = "",
    @DefaultEmptyString val recordVideoDir: String = "",
    val recordVideoSize: VideoSizeConfig? = null,
    val screenSize: ScreenSizeConfig? = null,
    @DefaultEmptyString val storageStatePath: String = "",
    @DefaultFalse val strictSelectors: Boolean = false,
    @DefaultEmptyString val timezoneId: String = "",
    @DefaultEmptyString val userAgent: String = "",
    val viewport: ViewportConfig? = null,
)
