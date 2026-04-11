package org.khorum.oss.euri.dsl.config

import com.microsoft.playwright.Browser
import org.khorum.oss.euri.dsl.common.toPath
import org.khorum.oss.euri.dsl.enums.BrowserColorScheme
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.MapDsl
import org.khorum.oss.konstellation.metaDsl.annotation.RootDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultEnum
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyMap
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultTrue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DISABLED
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DOES_NOT_HAVE
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DO_NOT
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.IS_NOT

/**
 * @property deviceScaleFactor (default: 1.0) sets a specific scale (e.g. 2) to scale the page. Useful if you have
 * different page sizes and want to emulate the same page size in the browser.
 * @property acceptDownloads (default: true) whether to automatically download all the attachments.
 * @property javaScriptEnabled (default: true) whether to enable JavaScript, disabling JavaScript will throw an error
 * if a page tries to use JavaScript.
 * @property bypassCsp (default: false) whether to bypass the page's Content-Security-Policy.
 * @property hasTouch (default: false) whether the browser supports touch events.
 * @property ignoreHttpsErrors (default: false) whether to ignore HTTPS errors while loading.
 * @property isMobile (default: false) whether the browser is running in mobile view.
 * @property offline (default: false) whether to emulate network being offline.
 * @property strictSelectors (default: false) whether to enable strict selectors.
 * @property colorScheme (default: "NO_PREFERENCE") whether to emulate given media type.
 * @property baseUrl (default: null) base URL to use in actions like `page.goto()`.
 * @property locale (default: null) locale to use (e.g. en-GB, fr-CH).
 * @property recordHarPath (default: null) path to the HAR file to record.
 * @property recordVideoDir (default: null) path to the directory to put record videos into.
 * @property storageStatePath (default: null) path to the file to read and write storage state from/to.
 * @property timezoneId (default: null) timezone id to use (e.g. Europe/Berlin).
 * @property userAgent (default: null) specific user agent to use in this browser instance.
 * @property extraHttpHeaders (default: emptyMap) additional HTTP headers to use in this browser context.
 * @property permissions (default: null) permissions to grant to all pages in this browser context.
 * @property geolocation (default: null) geolocation to use or `null` if not specified.
 * @property httpCredentials (default: null) http credentials to use or `null` if not specified.
 * @property proxy (default: null) proxy settings to use or `null` if not specified.
 * @property recordVideoSize (default: null) size of the videos to record.
 * @property screenSize (default: null) screen size to emulate.
 * @property viewport (default: null) viewport size to emulate.
 */
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

    @DefaultEnum(
        value = "NoPreference",
        packageName = "org.khorum.oss.euri.dsl.enums",
        className = "BrowserColorScheme"
    )
    val colorScheme: BrowserColorScheme = BrowserColorScheme.NoPreference,

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
        options.setColorScheme(colorScheme.toPlaywright())
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
