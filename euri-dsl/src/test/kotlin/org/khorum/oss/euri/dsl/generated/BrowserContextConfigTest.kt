package org.khorum.oss.euri.dsl.generated

import org.junit.jupiter.api.Test
import org.khorum.oss.euri.dsl.browserContextConfig
import org.khorum.oss.euri.dsl.config.BrowserContextConfig

class BrowserContextConfigTest {

    @Test
    fun `default config`() {
        val config = browserContextConfig {

        }

        val expected = BrowserContextConfig()

        assert(config == expected)
    }
}