package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.DefaultValue
import org.khorum.oss.konstellation.metaDsl.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.ListDsl
import org.khorum.oss.konstellation.metaDsl.MapDsl

@GeneratedDsl
data class BrowserContextConfig(
    @DefaultValue("true") val acceptDownloads: Boolean,
    @DefaultValue("\"\"") val baseURL: String,
    @DefaultValue("false") val bypassCSP: Boolean,
    @DefaultValue("\"NO_PREFERENCE\"") val colorScheme: String,
    @DefaultValue("1.0") val deviceScaleFactor: Double,
    @MapDsl val extraHTTPHeaders: Map<String, String>,
    @DefaultValue("false") val hasTouch: Boolean,
    val geolocation: GeolocationConfig?,
    val httpCredentials: HttpCredentialsConfig?,
    @DefaultValue("false") val ignoreHTTPSErrors: Boolean,
    @DefaultValue("false") val isMobile: Boolean,
    @DefaultValue("true") val javaScriptEnabled: Boolean,
    @DefaultValue("\"\"") val locale: String,
    @DefaultValue("false") val offline: Boolean,
    @ListDsl val permissions: List<String>,
    val proxy: ProxyConfig?,
    @DefaultValue("\"\"") val recordHarPath: String,
    @DefaultValue("\"\"") val recordVideoDir: String,
    val recordVideoSize: VideoSizeConfig?,
    val screenSize: ScreenSizeConfig?,
    @DefaultValue("\"\"") val storageStatePath: String,
    @DefaultValue("false") val strictSelectors: Boolean,
    @DefaultValue("\"\"") val timezoneId: String,
    @DefaultValue("\"\"") val userAgent: String,
    val viewport: ViewportConfig?,
)
