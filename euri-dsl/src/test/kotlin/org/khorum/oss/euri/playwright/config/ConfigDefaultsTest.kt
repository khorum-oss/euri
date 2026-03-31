package org.khorum.oss.euri.playwright.config

import org.junit.jupiter.api.Test
import org.khorum.oss.euri.dsl.config.BrowserContextConfig
import org.khorum.oss.euri.dsl.config.BrowserLaunchConfig
import org.khorum.oss.euri.dsl.config.ClipConfig
import org.khorum.oss.euri.dsl.config.GeolocationConfig
import org.khorum.oss.euri.dsl.config.HttpCredentialsConfig
import org.khorum.oss.euri.dsl.config.LocatorFilterConfig
import org.khorum.oss.euri.dsl.config.LocatorOptionsConfig
import org.khorum.oss.euri.dsl.config.NavigationConfig
import org.khorum.oss.euri.dsl.config.ProxyConfig
import org.khorum.oss.euri.dsl.config.ScreenSizeConfig
import org.khorum.oss.euri.dsl.config.ScreenshotConfig
import org.khorum.oss.euri.dsl.config.VideoSizeConfig
import org.khorum.oss.euri.dsl.config.ViewportConfig
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ConfigDefaultsTest {

    // ---- BrowserLaunchConfig defaults ----

    @Test
    fun `BrowserLaunchConfig default headless is true`() {
        assertEquals(true, BrowserLaunchConfig().headless)
    }

    @Test
    fun `BrowserLaunchConfig default channel is empty`() {
        assertEquals("", BrowserLaunchConfig().channel)
    }

    @Test
    fun `BrowserLaunchConfig default args is empty`() {
        assertEquals(emptyList(), BrowserLaunchConfig().args)
    }

    @Test
    fun `BrowserLaunchConfig default chromiumSandbox is false`() {
        assertFalse(BrowserLaunchConfig().chromiumSandbox)
    }

    @Test
    fun `BrowserLaunchConfig default downloadsPath is empty`() {
        assertEquals("", BrowserLaunchConfig().downloadsPath)
    }

    @Test
    fun `BrowserLaunchConfig default executablePath is empty`() {
        assertEquals("", BrowserLaunchConfig().executablePath)
    }

    @Test
    fun `BrowserLaunchConfig default env is empty`() {
        assertEquals(emptyMap(), BrowserLaunchConfig().env)
    }

    @Test
    fun `BrowserLaunchConfig default handleSIGHUP is true`() {
        assertTrue(BrowserLaunchConfig().handleSIGHUP)
    }

    @Test
    fun `BrowserLaunchConfig default handleSIGINT is true`() {
        assertTrue(BrowserLaunchConfig().handleSIGINT)
    }

    @Test
    fun `BrowserLaunchConfig default handleSIGTERM is true`() {
        assertTrue(BrowserLaunchConfig().handleSIGTERM)
    }

    @Test
    fun `BrowserLaunchConfig default ignoreAllDefaultArgs is false`() {
        assertFalse(BrowserLaunchConfig().ignoreAllDefaultArgs)
    }

    @Test
    fun `BrowserLaunchConfig default ignoreDefaultArgs is empty`() {
        assertEquals(emptyList(), BrowserLaunchConfig().ignoreDefaultArgs)
    }

    @Test
    fun `BrowserLaunchConfig default proxy is null`() {
        assertNull(BrowserLaunchConfig().proxy)
    }

    @Test
    fun `BrowserLaunchConfig default slowMo is 0`() {
        assertEquals(0.0, BrowserLaunchConfig().slowMo)
    }

    @Test
    fun `BrowserLaunchConfig default timeout is 30000`() {
        assertEquals(30000.0, BrowserLaunchConfig().timeout)
    }

    @Test
    fun `BrowserLaunchConfig default tracesDir is empty`() {
        assertEquals("", BrowserLaunchConfig().tracesDir)
    }

    // ---- BrowserContextConfig defaults ----

    @Test
    fun `BrowserContextConfig default acceptDownloads is true`() {
        assertTrue(BrowserContextConfig().acceptDownloads)
    }

    @Test
    fun `BrowserContextConfig default baseURL is empty`() {
        assertEquals("", BrowserContextConfig().baseURL)
    }

    @Test
    fun `BrowserContextConfig default bypassCSP is false`() {
        assertFalse(BrowserContextConfig().bypassCSP)
    }

    @Test
    fun `BrowserContextConfig default colorScheme is NO_PREFERENCE`() {
        assertEquals("NO_PREFERENCE", BrowserContextConfig().colorScheme)
    }

    @Test
    fun `BrowserContextConfig default deviceScaleFactor is 1_0`() {
        assertEquals(1.0, BrowserContextConfig().deviceScaleFactor)
    }

    @Test
    fun `BrowserContextConfig default extraHTTPHeaders is empty`() {
        assertEquals(emptyMap(), BrowserContextConfig().extraHTTPHeaders)
    }

    @Test
    fun `BrowserContextConfig default hasTouch is false`() {
        assertFalse(BrowserContextConfig().hasTouch)
    }

    @Test
    fun `BrowserContextConfig default geolocation is null`() {
        assertNull(BrowserContextConfig().geolocation)
    }

    @Test
    fun `BrowserContextConfig default httpCredentials is null`() {
        assertNull(BrowserContextConfig().httpCredentials)
    }

    @Test
    fun `BrowserContextConfig default ignoreHTTPSErrors is false`() {
        assertFalse(BrowserContextConfig().ignoreHTTPSErrors)
    }

    @Test
    fun `BrowserContextConfig default isMobile is false`() {
        assertFalse(BrowserContextConfig().isMobile)
    }

    @Test
    fun `BrowserContextConfig default javaScriptEnabled is true`() {
        assertTrue(BrowserContextConfig().javaScriptEnabled)
    }

    @Test
    fun `BrowserContextConfig default locale is empty`() {
        assertEquals("", BrowserContextConfig().locale)
    }

    @Test
    fun `BrowserContextConfig default offline is false`() {
        assertFalse(BrowserContextConfig().offline)
    }

    @Test
    fun `BrowserContextConfig default permissions is empty`() {
        assertEquals(emptyList(), BrowserContextConfig().permissions)
    }

    @Test
    fun `BrowserContextConfig default proxy is null`() {
        assertNull(BrowserContextConfig().proxy)
    }

    @Test
    fun `BrowserContextConfig default recordHarPath is empty`() {
        assertEquals("", BrowserContextConfig().recordHarPath)
    }

    @Test
    fun `BrowserContextConfig default recordVideoDir is empty`() {
        assertEquals("", BrowserContextConfig().recordVideoDir)
    }

    @Test
    fun `BrowserContextConfig default recordVideoSize is null`() {
        assertNull(BrowserContextConfig().recordVideoSize)
    }

    @Test
    fun `BrowserContextConfig default screenSize is null`() {
        assertNull(BrowserContextConfig().screenSize)
    }

    @Test
    fun `BrowserContextConfig default storageStatePath is empty`() {
        assertEquals("", BrowserContextConfig().storageStatePath)
    }

    @Test
    fun `BrowserContextConfig default strictSelectors is false`() {
        assertFalse(BrowserContextConfig().strictSelectors)
    }

    @Test
    fun `BrowserContextConfig default timezoneId is empty`() {
        assertEquals("", BrowserContextConfig().timezoneId)
    }

    @Test
    fun `BrowserContextConfig default userAgent is empty`() {
        assertEquals("", BrowserContextConfig().userAgent)
    }

    @Test
    fun `BrowserContextConfig default viewport is null`() {
        assertNull(BrowserContextConfig().viewport)
    }

    // ---- ViewportConfig defaults ----

    @Test
    fun `ViewportConfig default width is 1280`() {
        assertEquals(1280, ViewportConfig().width)
    }

    @Test
    fun `ViewportConfig default height is 720`() {
        assertEquals(720, ViewportConfig().height)
    }

    // ---- NavigationConfig defaults ----

    @Test
    fun `NavigationConfig default waitUntil is LOAD`() {
        assertEquals("LOAD", NavigationConfig(url = "https://example.com").waitUntil)
    }

    @Test
    fun `NavigationConfig default timeout is 30000`() {
        assertEquals(30000.0, NavigationConfig(url = "https://example.com").timeout)
    }

    @Test
    fun `NavigationConfig default referer is empty`() {
        assertEquals("", NavigationConfig(url = "https://example.com").referer)
    }

    // ---- ScreenshotConfig defaults ----

    @Test
    fun `ScreenshotConfig default path is empty`() {
        assertEquals("", ScreenshotConfig().path)
    }

    @Test
    fun `ScreenshotConfig default type is PNG`() {
        assertEquals("PNG", ScreenshotConfig().type)
    }

    @Test
    fun `ScreenshotConfig default quality is -1`() {
        assertEquals(-1, ScreenshotConfig().quality)
    }

    @Test
    fun `ScreenshotConfig default fullPage is false`() {
        assertFalse(ScreenshotConfig().fullPage)
    }

    @Test
    fun `ScreenshotConfig default clip is null`() {
        assertNull(ScreenshotConfig().clip)
    }

    @Test
    fun `ScreenshotConfig default omitBackground is false`() {
        assertFalse(ScreenshotConfig().omitBackground)
    }

    @Test
    fun `ScreenshotConfig default style is empty`() {
        assertEquals("", ScreenshotConfig().style)
    }

    @Test
    fun `ScreenshotConfig default maskSelectors is empty`() {
        assertEquals(emptyList(), ScreenshotConfig().maskSelectors)
    }

    // ---- ProxyConfig defaults ----

    @Test
    fun `ProxyConfig default bypass is empty`() {
        assertEquals("", ProxyConfig(server = "http://proxy:8080").bypass)
    }

    @Test
    fun `ProxyConfig default username is empty`() {
        assertEquals("", ProxyConfig(server = "http://proxy:8080").username)
    }

    @Test
    fun `ProxyConfig default password is empty`() {
        assertEquals("", ProxyConfig(server = "http://proxy:8080").password)
    }

    // ---- GeolocationConfig defaults ----

    @Test
    fun `GeolocationConfig default accuracy is 0`() {
        assertEquals(0.0, GeolocationConfig(latitude = 0.0, longitude = 0.0).accuracy)
    }

    // ---- HttpCredentialsConfig defaults ----

    @Test
    fun `HttpCredentialsConfig default origin is empty`() {
        assertEquals("", HttpCredentialsConfig(username = "u", password = "p").origin)
    }

    @Test
    fun `HttpCredentialsConfig default sendImmediately is false`() {
        assertFalse(HttpCredentialsConfig(username = "u", password = "p").sendImmediately)
    }

    // ---- LocatorOptionsConfig defaults ----

    @Test
    fun `LocatorOptionsConfig default hasText is empty`() {
        assertEquals("", LocatorOptionsConfig().hasText)
    }

    @Test
    fun `LocatorOptionsConfig default hasNotText is empty`() {
        assertEquals("", LocatorOptionsConfig().hasNotText)
    }

    // ---- LocatorFilterConfig defaults ----

    @Test
    fun `LocatorFilterConfig default hasText is empty`() {
        assertEquals("", LocatorFilterConfig().hasText)
    }

    @Test
    fun `LocatorFilterConfig default hasNotText is empty`() {
        assertEquals("", LocatorFilterConfig().hasNotText)
    }

    // ---- ClipConfig (no defaults - all required) ----

    @Test
    fun `ClipConfig holds all values`() {
        val clip = ClipConfig(x = 10.0, y = 20.0, width = 100.0, height = 200.0)
        assertEquals(10.0, clip.x)
        assertEquals(20.0, clip.y)
        assertEquals(100.0, clip.width)
        assertEquals(200.0, clip.height)
    }

    // ---- ScreenSizeConfig (no defaults - all required) ----

    @Test
    fun `ScreenSizeConfig holds all values`() {
        val config = ScreenSizeConfig(width = 1920, height = 1080)
        assertEquals(1920, config.width)
        assertEquals(1080, config.height)
    }

    // ---- VideoSizeConfig (no defaults - all required) ----

    @Test
    fun `VideoSizeConfig holds all values`() {
        val config = VideoSizeConfig(width = 1280, height = 720)
        assertEquals(1280, config.width)
        assertEquals(720, config.height)
    }

    // ---- Data class copy/equality ----

    @Test
    fun `BrowserLaunchConfig copy preserves values`() {
        val original = BrowserLaunchConfig(headless = false, channel = "chrome")
        val copy = original.copy(timeout = 5000.0)
        assertFalse(copy.headless)
        assertEquals("chrome", copy.channel)
        assertEquals(5000.0, copy.timeout)
    }

    @Test
    fun `BrowserContextConfig copy preserves values`() {
        val original = BrowserContextConfig(locale = "en-US", offline = true)
        val copy = original.copy(baseURL = "https://example.com")
        assertEquals("en-US", copy.locale)
        assertTrue(copy.offline)
        assertEquals("https://example.com", copy.baseURL)
    }

    @Test
    fun `NavigationConfig equality`() {
        val a = NavigationConfig(url = "https://example.com")
        val b = NavigationConfig(url = "https://example.com")
        assertEquals(a, b)
    }

    @Test
    fun `ScreenshotConfig equality`() {
        val a = ScreenshotConfig(path = "/tmp/a.png", fullPage = true)
        val b = ScreenshotConfig(path = "/tmp/a.png", fullPage = true)
        assertEquals(a, b)
    }
}
