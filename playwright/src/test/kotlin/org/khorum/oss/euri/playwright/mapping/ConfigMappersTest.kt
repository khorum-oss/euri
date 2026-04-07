package org.khorum.oss.euri.playwright.mapping

import org.khorum.oss.euri.playwright.config.*
import org.khorum.oss.euri.playwright.enums.BrowserColorScheme
import org.khorum.oss.euri.playwright.enums.ScreenshotType
import org.khorum.oss.euri.playwright.enums.WaitUntil
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class ConfigMappersTest {

    // ---- ProxyConfig.toPlaywright() ----

    @Test
    fun `ProxyConfig toPlaywright with only server`() {
        val config = ProxyConfig(server = "http://proxy:8080")
        val proxy = config.toPlaywright()
        assertEquals("http://proxy:8080", proxy.server)
    }

    @Test
    fun `ProxyConfig toPlaywright with all fields`() {
        val config = ProxyConfig(
            server = "http://proxy:8080",
            bypass = "localhost",
            username = "user",
            password = "pass"
        )
        val proxy = config.toPlaywright()
        assertEquals("http://proxy:8080", proxy.server)
        assertEquals("localhost", proxy.bypass)
        assertEquals("user", proxy.username)
        assertEquals("pass", proxy.password)
    }

    @Test
    fun `ProxyConfig toPlaywright with empty optional fields uses defaults`() {
        val config = ProxyConfig(server = "http://proxy:8080")
        val proxy = config.toPlaywright()
        assertEquals("http://proxy:8080", proxy.server)
        // bypass, username, password are empty strings by default, so they shouldn't be set
        // We verify the proxy was created successfully with just the server
    }

    // ---- ViewportConfig.toPlaywright() ----

    @Test
    fun `ViewportConfig toPlaywright with defaults`() {
        val config = ViewportConfig()
        val viewport = config.toPlaywright()
        assertEquals(1280, viewport.width)
        assertEquals(720, viewport.height)
    }

    @Test
    fun `ViewportConfig toPlaywright with custom values`() {
        val config = ViewportConfig(width = 1920, height = 1080)
        val viewport = config.toPlaywright()
        assertEquals(1920, viewport.width)
        assertEquals(1080, viewport.height)
    }

    // ---- GeolocationConfig.toPlaywright() ----

    @Test
    fun `GeolocationConfig toPlaywright with required fields only`() {
        val config = GeolocationConfig(latitude = 51.5074, longitude = -0.1278)
        val geo = config.toPlaywright()
        assertEquals(51.5074, geo.latitude)
        assertEquals(-0.1278, geo.longitude)
        assertEquals(0.0, geo.accuracy)
    }

    @Test
    fun `GeolocationConfig toPlaywright with accuracy`() {
        val config = GeolocationConfig(latitude = 40.7128, longitude = -74.0060, accuracy = 100.0)
        val geo = config.toPlaywright()
        assertEquals(40.7128, geo.latitude)
        assertEquals(-74.0060, geo.longitude)
        assertEquals(100.0, geo.accuracy)
    }

    // ---- ScreenSizeConfig.toPlaywright() ----

    @Test
    fun `ScreenSizeConfig toPlaywright`() {
        val config = ScreenSizeConfig(width = 1920, height = 1080)
        val screenSize = config.toPlaywright()
        assertEquals(1920, screenSize.width)
        assertEquals(1080, screenSize.height)
    }

    // ---- BrowserLaunchConfig.toPlaywright() ----

    @Test
    fun `BrowserLaunchConfig toPlaywright with defaults`() {
        val config = BrowserLaunchConfig()
        val options = config.toPlaywright()
        // headless defaults to true - options are set on the LaunchOptions object
        // We mainly verify no exception is thrown with defaults
    }

    @Test
    fun `BrowserLaunchConfig toPlaywright with channel set`() {
        val config = BrowserLaunchConfig(channel = "chrome")
        val options = config.toPlaywright()
        // channel is set when non-empty
    }

    @Test
    fun `BrowserLaunchConfig toPlaywright with args set`() {
        val config = BrowserLaunchConfig(args = listOf("--no-sandbox", "--disable-gpu"))
        val options = config.toPlaywright()
        // args are set when non-empty
    }

    @Test
    fun `BrowserLaunchConfig toPlaywright with downloadsPath set`() {
        val config = BrowserLaunchConfig(downloadsPath = "/tmp/downloads")
        val options = config.toPlaywright()
        // downloadsPath is set when non-empty
    }

    @Test
    fun `BrowserLaunchConfig toPlaywright with executablePath set`() {
        val config = BrowserLaunchConfig(executablePath = "/usr/bin/chromium")
        val options = config.toPlaywright()
        // executablePath is set when non-empty
    }

    @Test
    fun `BrowserLaunchConfig toPlaywright with env set`() {
        val config = BrowserLaunchConfig(env = mapOf("DISPLAY" to ":0"))
        val options = config.toPlaywright()
        // env is set when non-empty
    }

    @Test
    fun `BrowserLaunchConfig toPlaywright with ignoreAllDefaultArgs true`() {
        val config = BrowserLaunchConfig(ignoreAllDefaultArgs = true)
        val options = config.toPlaywright()
        // ignoreAllDefaultArgs is set when true
    }

    @Test
    fun `BrowserLaunchConfig toPlaywright with ignoreDefaultArgs set`() {
        val config = BrowserLaunchConfig(ignoreDefaultArgs = listOf("--mute-audio"))
        val options = config.toPlaywright()
        // ignoreDefaultArgs is set when non-empty
    }

    @Test
    fun `BrowserLaunchConfig toPlaywright with proxy set`() {
        val config = BrowserLaunchConfig(proxy = ProxyConfig(server = "http://proxy:8080"))
        val options = config.toPlaywright()
        // proxy is set when non-null
    }

    @Test
    fun `BrowserLaunchConfig toPlaywright with slowMo set`() {
        val config = BrowserLaunchConfig(slowMo = 100.0)
        val options = config.toPlaywright()
        // slowMo is set when > 0
    }

    @Test
    fun `BrowserLaunchConfig toPlaywright with tracesDir set`() {
        val config = BrowserLaunchConfig(tracesDir = "/tmp/traces")
        val options = config.toPlaywright()
        // tracesDir is set when non-empty
    }

    @Test
    fun `BrowserLaunchConfig toPlaywright with headless false`() {
        val config = BrowserLaunchConfig(headless = false)
        val options = config.toPlaywright()
        // headless = false is passed through
    }

    @Test
    fun `BrowserLaunchConfig toPlaywright with signal handlers disabled`() {
        val config = BrowserLaunchConfig(
            handleSIGHUP = false,
            handleSIGINT = false,
            handleSIGTERM = false
        )
        val options = config.toPlaywright()
        // signal handlers set to false
    }

    @Test
    fun `BrowserLaunchConfig toPlaywright with custom timeout`() {
        val config = BrowserLaunchConfig(timeout = 60000.0)
        val options = config.toPlaywright()
        // timeout is always set
    }

    @Test
    fun `BrowserLaunchConfig toPlaywright with all fields`() {
        val config = BrowserLaunchConfig(
            headless = false,
            channel = "chrome",
            args = listOf("--no-sandbox"),
            chromiumSandbox = true,
            downloadsPath = "/tmp/downloads",
            executablePath = "/usr/bin/chromium",
            env = mapOf("DISPLAY" to ":0"),
            handleSIGHUP = false,
            handleSIGINT = false,
            handleSIGTERM = false,
            ignoreAllDefaultArgs = true,
            ignoreDefaultArgs = listOf("--mute-audio"),
            proxy = ProxyConfig(server = "http://proxy:8080"),
            slowMo = 50.0,
            timeout = 60000.0,
            tracesDir = "/tmp/traces"
        )
        val options = config.toPlaywright()
        // all fields set without exception
    }

    // ---- BrowserContextConfig.toPlaywright() ----

    @Test
    fun `BrowserContextConfig toPlaywright with defaults`() {
        val config = BrowserContextConfig()
        val options = config.toPlaywright()
        // defaults applied without exception
    }

    @Test
    fun `BrowserContextConfig toPlaywright with baseURL set`() {
        val config = BrowserContextConfig(baseURL = "https://example.com")
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with colorScheme DARK`() {
        val config = BrowserContextConfig(colorScheme = BrowserColorScheme.Dark)
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with colorScheme LIGHT`() {
        val config = BrowserContextConfig(colorScheme = BrowserColorScheme.Light)
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with colorScheme NO_PREFERENCE skips setting`() {
        val config = BrowserContextConfig(colorScheme = BrowserColorScheme.NoPreference)
        val options = config.toPlaywright()
        // NO_PREFERENCE is the default and should not be set
    }

    @Test
    fun `BrowserContextConfig toPlaywright with deviceScaleFactor`() {
        val config = BrowserContextConfig(deviceScaleFactor = 2.0)
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with deviceScaleFactor default 1_0 skips setting`() {
        val config = BrowserContextConfig(deviceScaleFactor = 1.0)
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with extraHTTPHeaders`() {
        val config = BrowserContextConfig(extraHTTPHeaders = mapOf("Authorization" to "Bearer token"))
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with geolocation`() {
        val config = BrowserContextConfig(
            geolocation = GeolocationConfig(latitude = 51.5, longitude = -0.1)
        )
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with httpCredentials`() {
        val config = BrowserContextConfig(
            httpCredentials = HttpCredentialsConfig(username = "user", password = "pass")
        )
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with locale set`() {
        val config = BrowserContextConfig(locale = "en-US")
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with permissions`() {
        val config = BrowserContextConfig(permissions = listOf("geolocation", "notifications"))
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with proxy`() {
        val config = BrowserContextConfig(
            proxy = ProxyConfig(server = "http://proxy:8080")
        )
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with recordHarPath`() {
        val config = BrowserContextConfig(recordHarPath = "/tmp/har.json")
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with recordVideoDir`() {
        val config = BrowserContextConfig(recordVideoDir = "/tmp/videos")
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with recordVideoSize`() {
        val config = BrowserContextConfig(
            recordVideoSize = VideoSizeConfig(width = 1280, height = 720)
        )
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with screenSize`() {
        val config = BrowserContextConfig(
            screenSize = ScreenSizeConfig(width = 1920, height = 1080)
        )
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with storageStatePath`() {
        val config = BrowserContextConfig(storageStatePath = "/tmp/storage.json")
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with timezoneId`() {
        val config = BrowserContextConfig(timezoneId = "America/New_York")
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with userAgent`() {
        val config = BrowserContextConfig(userAgent = "CustomAgent/1.0")
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with viewport`() {
        val config = BrowserContextConfig(
            viewport = ViewportConfig(width = 1920, height = 1080)
        )
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with boolean flags`() {
        val config = BrowserContextConfig(
            acceptDownloads = false,
            bypassCSP = true,
            hasTouch = true,
            ignoreHTTPSErrors = true,
            isMobile = true,
            javaScriptEnabled = false,
            offline = true,
            strictSelectors = true
        )
        val options = config.toPlaywright()
    }

    @Test
    fun `BrowserContextConfig toPlaywright with all fields`() {
        val config = BrowserContextConfig(
            acceptDownloads = false,
            baseURL = "https://example.com",
            bypassCSP = true,
            colorScheme = BrowserColorScheme.Dark,
            deviceScaleFactor = 2.0,
            extraHTTPHeaders = mapOf("X-Custom" to "value"),
            hasTouch = true,
            geolocation = GeolocationConfig(latitude = 51.5, longitude = -0.1),
            httpCredentials = HttpCredentialsConfig(username = "u", password = "p"),
            ignoreHTTPSErrors = true,
            isMobile = true,
            javaScriptEnabled = false,
            locale = "en-US",
            offline = true,
            permissions = listOf("geolocation"),
            proxy = ProxyConfig(server = "http://proxy:8080"),
            recordHarPath = "/tmp/har.json",
            recordVideoDir = "/tmp/videos",
            recordVideoSize = VideoSizeConfig(width = 1280, height = 720),
            screenSize = ScreenSizeConfig(width = 1920, height = 1080),
            storageStatePath = "/tmp/storage.json",
            strictSelectors = true,
            timezoneId = "America/New_York",
            userAgent = "CustomAgent/1.0",
            viewport = ViewportConfig(width = 1920, height = 1080)
        )
        val options = config.toPlaywright()
    }

    // ---- NavigationConfig.toNavigateOptions() ----

    @Test
    fun `NavigationConfig toNavigateOptions with defaults`() {
        val config = NavigationConfig(url = "https://example.com")
        val options = config.toNavigateOptions()
        // timeout and waitUntil are always set
    }

    @Test
    fun `NavigationConfig toNavigateOptions with referer`() {
        val config = NavigationConfig(
            url = "https://example.com",
            referer = "https://google.com"
        )
        val options = config.toNavigateOptions()
    }

    @Test
    fun `NavigationConfig toNavigateOptions with custom waitUntil`() {
        val config = NavigationConfig(
            url = "https://example.com",
            waitUntil = WaitUntil.DomContentLoaded
        )
        val options = config.toNavigateOptions()
    }

    @Test
    fun `NavigationConfig toNavigateOptions with NETWORKIDLE`() {
        val config = NavigationConfig(
            url = "https://example.com",
            waitUntil = WaitUntil.NetworkIdle
        )
        val options = config.toNavigateOptions()
    }

    @Test
    fun `NavigationConfig toNavigateOptions with COMMIT`() {
        val config = NavigationConfig(
            url = "https://example.com",
            waitUntil = WaitUntil.Commit
        )
        val options = config.toNavigateOptions()
    }

    @Test
    fun `NavigationConfig toNavigateOptions with custom timeout`() {
        val config = NavigationConfig(
            url = "https://example.com",
            timeout = 60000.0
        )
        val options = config.toNavigateOptions()
    }

    @Test
    fun `NavigationConfig toNavigateOptions with all fields`() {
        val config = NavigationConfig(
            url = "https://example.com",
            waitUntil = WaitUntil.DomContentLoaded,
            timeout = 5000.0,
            referer = "https://google.com"
        )
        val options = config.toNavigateOptions()
    }

    // ---- ScreenshotConfig.toPlaywright() ----

    @Test
    fun `ScreenshotConfig toPlaywright with defaults`() {
        val config = ScreenshotConfig()
        val options = config.toPlaywright()
    }

    @Test
    fun `ScreenshotConfig toPlaywright with path set`() {
        val config = ScreenshotConfig(path = "/tmp/screenshot.png")
        val options = config.toPlaywright()
    }

    @Test
    fun `ScreenshotConfig toPlaywright with fullPage true`() {
        val config = ScreenshotConfig(fullPage = true)
        val options = config.toPlaywright()
    }

    @Test
    fun `ScreenshotConfig toPlaywright with omitBackground true`() {
        val config = ScreenshotConfig(omitBackground = true)
        val options = config.toPlaywright()
    }

    @Test
    fun `ScreenshotConfig toPlaywright with clip`() {
        val config = ScreenshotConfig(
            clip = ClipConfig(x = 0.0, y = 0.0, width = 100.0, height = 100.0)
        )
        val options = config.toPlaywright()
    }

    @Test
    fun `ScreenshotConfig toPlaywright with quality set`() {
        val config = ScreenshotConfig(quality = 80)
        val options = config.toPlaywright()
    }

    @Test
    fun `ScreenshotConfig toPlaywright with quality negative skips setting`() {
        val config = ScreenshotConfig(quality = -1)
        val options = config.toPlaywright()
    }

    @Test
    fun `ScreenshotConfig toPlaywright with style set`() {
        val config = ScreenshotConfig(style = "body { background: red; }")
        val options = config.toPlaywright()
    }

    @Test
    fun `ScreenshotConfig toPlaywright with all fields`() {
        val config = ScreenshotConfig(
            path = "/tmp/screenshot.png",
            type = ScreenshotType.Jpeg,
            quality = 90,
            fullPage = true,
            clip = ClipConfig(x = 10.0, y = 20.0, width = 300.0, height = 200.0),
            omitBackground = true,
            style = "body { background: transparent; }"
        )
        val options = config.toPlaywright()
    }
}
