package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultEmptyList
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultEmptyMap
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultEmptyString
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultNull
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.MapDsl

@GeneratedDsl
@DefaultState
data class BrowserContextConfig(
    @DefaultValue("true") val acceptDownloads: Boolean = true,
    @DefaultEmptyString val baseURL: String = "",
    @DefaultValue("false") val bypassCSP: Boolean = false,
    @DefaultValue("NO_PREFERENCE") val colorScheme: String = "NO_PREFERENCE",
    @DefaultValue("1.0") val deviceScaleFactor: Double = 1.0,
    @DefaultEmptyMap @MapDsl val extraHTTPHeaders: Map<String, String> = emptyMap(),
    @DefaultValue("false") val hasTouch: Boolean = false,
    @DefaultNull val geolocation: GeolocationConfig? = null,
    @DefaultNull val httpCredentials: HttpCredentialsConfig? = null,
    @DefaultValue("false") val ignoreHTTPSErrors: Boolean = false,
    @DefaultValue("false") val isMobile: Boolean = false,
    @DefaultValue("true") val javaScriptEnabled: Boolean = true,
    @DefaultEmptyString val locale: String = "",
    @DefaultValue("false") val offline: Boolean = false,
    @DefaultEmptyList @ListDsl val permissions: List<String> = emptyList(),
    @DefaultNull val proxy: ProxyConfig? = null,
    @DefaultEmptyString val recordHarPath: String = "",
    @DefaultEmptyString val recordVideoDir: String = "",
    @DefaultNull val recordVideoSize: VideoSizeConfig? = null,
    @DefaultNull val screenSize: ScreenSizeConfig? = null,
    @DefaultEmptyString val storageStatePath: String = "",
    @DefaultValue("false") val strictSelectors: Boolean = false,
    @DefaultEmptyString val timezoneId: String = "",
    @DefaultEmptyString val userAgent: String = "",
    @DefaultNull val viewport: ViewportConfig? = null,
)
