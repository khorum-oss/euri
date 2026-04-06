package org.khorum.oss.euri.dsl.generated

import org.junit.jupiter.api.Test
import org.khorum.oss.euri.dsl.browserContextConfig
import org.khorum.oss.euri.dsl.config.BrowserContextConfig
import org.khorum.oss.euri.dsl.config.GeolocationConfig
import org.khorum.oss.euri.dsl.config.HttpCredentialsConfig
import org.khorum.oss.euri.dsl.config.ProxyConfig
import org.khorum.oss.euri.dsl.config.ScreenSizeConfig
import org.khorum.oss.euri.dsl.config.VideoSizeConfig
import org.khorum.oss.euri.dsl.config.ViewportConfig

class BrowserContextConfigTest {

    @Test
    fun `default minimum config`() {
        val config = browserContextConfig {

        }

        val expected = BrowserContextConfig(
            acceptDownloads = true,
            baseUrl = null,
            bypassCsp = false,
            colorScheme = "NO_PREFERENCE",
            deviceScaleFactor = 1.0,
            extraHttpHeaders = emptyMap(),
            hasTouch = false,
            geolocation = null,
            httpCredentials = null,
            ignoreHttpsErrors = false,
            isMobile = false,
            javaScriptEnabled = true,
            locale = null,
            offline = false,
            permissions = null,
            proxy = null,
            recordHarPath = null,
            recordVideoDir = null,
            recordVideoSize = null,
            screenSize = null,
            storageStatePath = null,
            strictSelectors = false,
            timezoneId = null,
            userAgent = null,
            viewport = null
        )

        assert(config == expected) {
            "EXPECT: $expected\nACTUAL: $config"
        }
    }

    @Test
    fun `default complete config`() {
        val config = browserContextConfig {
            acceptDownloads()
            baseUrl = "http://localhost:8080"
            bypassCsp()
            colorScheme = "Synthwave"
            deviceScaleFactor = 2.0
            extraHttpHeaders(
                "X-Custom" to "first",
                "X-Addition" to "second"
            )
            hasTouch()
            geolocation {
                latitude = 51.5
                longitude = -0.1
                accuracy = 100.0
            }
            httpCredentials {
                username = "user"
                password = "password1234"
                origin = "http://localhost:8080"
                sendImmediately()
            }
            ignoreHttpsErrors()
            isMobile()
            javaScriptEnabled()
            locale = "en-US"
            offline()
            permissions("geolocation", "notifications")
            proxy {
                server = "http://proxy:8080"
                bypass = "localhost"
                username = "user"
                password = "password1234"
            }
            recordHarPath = "/tmp/har.json"
            recordVideoDir = "/tmp/videos"
            recordVideoSize {
                width = 1280
                height = 720
            }
            screenSize {
                width = 1920
                height = 1080
            }
            storageStatePath = "/tmp/storage.json"
            strictSelectors()
            timezoneId = "America/New_York"
            userAgent = "CustomAgent/1.0"
            viewport {
                width = 1920
                height = 1080
            }
        }

        val expected = BrowserContextConfig(
            acceptDownloads = true,
            baseUrl = "http://localhost:8080",
            bypassCsp = true,
            colorScheme = "Synthwave",
            deviceScaleFactor = 2.0,
            extraHttpHeaders = mapOf("X-Custom" to "first", "X-Addition" to "second"),
            hasTouch =  true,
            geolocation = GeolocationConfig(latitude = 51.5, longitude = -0.1, accuracy = 100.0),
            httpCredentials = HttpCredentialsConfig(
                username = "user",
                password = "password1234",
                origin = "http://localhost:8080",
                sendImmediately = true
            ),
            ignoreHttpsErrors = true,
            isMobile = true,
            javaScriptEnabled = true,
            locale = "en-US",
            offline = true,
            permissions = listOf("geolocation", "notifications"),
            proxy = ProxyConfig(
                server = "http://proxy:8080",
                bypass = "localhost",
                username = "user",
                password = "password1234"
            ),
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

        assert(config == expected) {
            "EXPECT: $expected\nACTUAL: $config"
        }
    }

    @Test
    fun `replacement boolean check`() {
        val config = browserContextConfig {
            doNotAcceptDownloads()
            doNotBypassCsp()
            doesNotHaveTouch()
            httpCredentials {
                username = "test"
                password = "test"
                doNotSendImmediately()
            }
            doNotIgnoreHttpsErrors()
            isNotMobile()
            javaScriptDisabled()
            online()
            nonStrictSelectors()
        }

        val expected = BrowserContextConfig(
            acceptDownloads = false,
            bypassCsp = false,
            hasTouch = false,
            httpCredentials = HttpCredentialsConfig(
                username = "test",
                password = "test",
                sendImmediately = false
            ),
            ignoreHttpsErrors = false,
            isMobile = false,
            javaScriptEnabled = false,
            offline = false,
            strictSelectors = false,
        )

        assert(config == expected) {
            "EXPECT: $expected\nACTUAL: $config"
        }
    }
}