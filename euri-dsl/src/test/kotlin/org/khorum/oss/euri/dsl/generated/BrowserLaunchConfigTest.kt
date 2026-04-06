package org.khorum.oss.euri.dsl.generated

import org.junit.jupiter.api.Test
import org.khorum.oss.euri.dsl.browserLaunchConfig
import org.khorum.oss.euri.dsl.config.BrowserLaunchConfig
import org.khorum.oss.euri.dsl.config.ProxyConfig

class BrowserLaunchConfigTest {
    @Test
    fun `default minimum config`() {
        val config = browserLaunchConfig {}

        val expected = BrowserLaunchConfig(
            headless = true,
            channel = null,
            args = null,
            chromiumSandbox = false,
            downloadsPath = null,
            executablePath = null,
            env = null,
            handleSighup = true,
            handleSigint = true,
            handleSigterm = true,
            ignoreAllDefaultArgs = false,
            ignoreDefaultArgs = null,
            proxy = null,
            slowMo = 0.0,
            timeout = 30000.0,
            tracesDir = null
        )

        assert(config == expected) {
            "EXPECT: $expected\nACTUAL: $config"
        }
    }

    @Test
    fun `default complete config`() {
        val config = browserLaunchConfig {
            headless()
            channel = "main"
            args("arg1", "arg2")
            chromiumSandbox()
            downloadsPath = "tmp/downloads"
            executablePath = "/usr/bin/chromium"
            env("MY_ENV" to "my_value")
            handleSighup()
            handleSigint()
            handleSigterm()
            ignoreAllDefaultArgs()
            ignoreDefaultArgs("ignore-this-arg")
            proxy {
                server = "http://proxy:8080"
            }
            slowMo = 50.0
            timeout = 60000.0
            tracesDir = "tmp/traces"
        }

        val expected = BrowserLaunchConfig(
            headless = true,
            channel = "main",
            args = listOf("arg1", "arg2"),
            chromiumSandbox = true,
            downloadsPath = "tmp/downloads",
            executablePath = "/usr/bin/chromium",
            env = mapOf("MY_ENV" to "my_value"),
            handleSighup = true,
            handleSigint = true,
            handleSigterm = true,
            ignoreAllDefaultArgs = true,
            ignoreDefaultArgs = listOf("ignore-this-arg"),
            proxy = ProxyConfig(server = "http://proxy:8080"),
            slowMo = 50.0,
            timeout = 60000.0,
            tracesDir = "tmp/traces"
        )

        assert(config == expected) {
            "EXPECT: $expected\nACTUAL: $config"
        }
    }

    @Test
    fun `replacement boolean check`() {
        val config = browserLaunchConfig {
            notHeadless()
            notChromiumSandbox()
            doNotHandleSighup()
            doNotHandleSigint()
            doNotHandleSigterm()
            doNotIgnoreAllDefaultArgs()
        }

        val expected = BrowserLaunchConfig(
            headless = false,
            chromiumSandbox = false,
            handleSighup = false,
            handleSigint = false,
            handleSigterm = false,
            ignoreAllDefaultArgs = false
        )

        assert(config == expected) {
            "EXPECT: $expected\nACTUAL: $config"
        }
    }
}