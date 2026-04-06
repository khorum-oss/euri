package org.khorum.oss.euri.dsl.config

import com.microsoft.playwright.Browser
import com.microsoft.playwright.options.ColorScheme
import org.khorum.oss.euri.dsl.common.toPath
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.MapDsl
import org.khorum.oss.konstellation.metaDsl.annotation.RootDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyMap
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultTrue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DISABLED
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DOES_NOT_HAVE
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DO_NOT
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.IS_NOT

@RootDsl
@GeneratedDsl
data class BrowserContextConfig(
    @DefaultValue("1.0") val deviceScaleFactor: Double = 1.0,

    @DefaultTrue(negationTemplate = DO_NOT)                    val acceptDownloads: Boolean = true,
    @DefaultTrue(negationTemplate = DISABLED)                  val javaScriptEnabled: Boolean = true,
    @DefaultFalse(negationTemplate = DO_NOT)                   val bypassCsp: Boolean = false,
    @DefaultFalse(negationTemplate = DOES_NOT_HAVE)            val hasTouch: Boolean = false,
    @DefaultFalse(negationTemplate = DO_NOT)                   val ignoreHttpsErrors: Boolean = false,
    @DefaultFalse(negationTemplate = IS_NOT)                   val isMobile: Boolean = false,
    @DefaultFalse(negationFunctionName = "online")             val offline: Boolean = false,
    @DefaultFalse(negationFunctionName = "nonStrictSelectors") val strictSelectors: Boolean = false,

    @DefaultValue("NO_PREFERENCE")
    val colorScheme: String = "NO_PREFERENCE",

    val baseUrl: String? = null,
    val locale: String? = null,
    val recordHarPath: String? = null,
    val recordVideoDir: String? = null,
    val storageStatePath: String? = null,
    val timezoneId: String? = null,
    val userAgent: String? = null,

    @DefaultEmptyMap @MapDsl val extraHttpHeaders: Map<String, String> = emptyMap(),

    @ListDsl val permissions: List<String>? = null,

    val geolocation: GeolocationConfig? = null,
    val httpCredentials: HttpCredentialsConfig? = null,
    val proxy: ProxyConfig? = null,
    val recordVideoSize: VideoSizeConfig? = null,
    val screenSize: ScreenSizeConfig? = null,
    val viewport: ViewportConfig? = null
) : PlaywrightConfig<Browser.NewContextOptions> {
    override fun toPlaywright(): Browser.NewContextOptions = Browser.NewContextOptions().also { options ->
        options.setAcceptDownloads(acceptDownloads)
        options.setBaseURL(baseUrl)
        options.setBypassCSP(bypassCsp)
        options.setColorScheme(ColorScheme.valueOf(colorScheme))
        options.setDeviceScaleFactor(deviceScaleFactor)
        options.setExtraHTTPHeaders(extraHttpHeaders)
        options.setHasTouch(hasTouch)

        geolocation?.let { options.setGeolocation(it.latitude, it.longitude) }

        httpCredentials?.let { options.setHttpCredentials(it.username, it.password) }

        options.setIgnoreHTTPSErrors(ignoreHttpsErrors)
        options.setIsMobile(isMobile)
        options.setJavaScriptEnabled(javaScriptEnabled)

        options.setLocale(locale)

        options.setOffline(offline)

        options.setPermissions(permissions)

        options.setProxy(proxy?.server)

        options.setRecordHarPath(recordHarPath?.toPath())
        options.setRecordVideoDir(recordVideoDir?.toPath())

        recordVideoSize?.let { options.setRecordVideoSize(it.width, it.height) }

        screenSize?.let { options.setScreenSize(it.width, it.height) }

        options.setStorageStatePath(storageStatePath?.toPath())

        options.setStrictSelectors(strictSelectors)

        options.setTimezoneId(timezoneId)
        options.setUserAgent(userAgent)

        viewport?.let { options.setViewportSize(it.width, it.height) }
    }
}
