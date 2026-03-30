package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.MapDsl

@GeneratedDsl
data class BrowserContextConfig(
    val acceptDownloads: Boolean = true,
    val baseURL: String = "",
    val bypassCSP: Boolean = false,
    val colorScheme: String = "NO_PREFERENCE",
    val deviceScaleFactor: Double = 1.0,
    @MapDsl val extraHTTPHeaders: Map<String, String> = emptyMap(),
    val hasTouch: Boolean = false,
    val geolocation: GeolocationConfig? = null,
    val httpCredentials: HttpCredentialsConfig? = null,
    val ignoreHTTPSErrors: Boolean = false,
    val isMobile: Boolean = false,
    val javaScriptEnabled: Boolean = true,
    val locale: String = "",
    val offline: Boolean = false,
    @ListDsl val permissions: List<String> = emptyList(),
    val proxy: ProxyConfig? = null,
    val recordHarPath: String = "",
    val recordVideoDir: String = "",
    val recordVideoSize: VideoSizeConfig? = null,
    val screenSize: ScreenSizeConfig? = null,
    val storageStatePath: String = "",
    val strictSelectors: Boolean = false,
    val timezoneId: String = "",
    val userAgent: String = "",
    val viewport: ViewportConfig? = null,
)
