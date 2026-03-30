package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultStateType
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.MapDsl

@GeneratedDsl
data class BrowserContextConfig(
    @DefaultState(type = DefaultStateType.TRUE) val acceptDownloads: Boolean = true,
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val baseURL: String = "",
    @DefaultState(type = DefaultStateType.FALSE) val bypassCSP: Boolean = false,
    @DefaultValue("NO_PREFERENCE") val colorScheme: String = "NO_PREFERENCE",
    @DefaultValue("1.0") val deviceScaleFactor: Double = 1.0,
    @MapDsl val extraHTTPHeaders: Map<String, String> = emptyMap(),
    @DefaultState(type = DefaultStateType.FALSE) val hasTouch: Boolean = false,
    val geolocation: GeolocationConfig? = null,
    val httpCredentials: HttpCredentialsConfig? = null,
    @DefaultState(type = DefaultStateType.FALSE) val ignoreHTTPSErrors: Boolean = false,
    @DefaultState(type = DefaultStateType.FALSE) val isMobile: Boolean = false,
    @DefaultState(type = DefaultStateType.TRUE) val javaScriptEnabled: Boolean = true,
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val locale: String = "",
    @DefaultState(type = DefaultStateType.FALSE) val offline: Boolean = false,
    @ListDsl val permissions: List<String> = emptyList(),
    val proxy: ProxyConfig? = null,
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val recordHarPath: String = "",
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val recordVideoDir: String = "",
    val recordVideoSize: VideoSizeConfig? = null,
    val screenSize: ScreenSizeConfig? = null,
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val storageStatePath: String = "",
    @DefaultState(type = DefaultStateType.FALSE) val strictSelectors: Boolean = false,
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val timezoneId: String = "",
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val userAgent: String = "",
    val viewport: ViewportConfig? = null,
)
