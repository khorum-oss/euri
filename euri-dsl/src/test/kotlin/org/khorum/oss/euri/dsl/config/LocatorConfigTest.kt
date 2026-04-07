package org.khorum.oss.euri.dsl.config

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LocatorConfigTest {

    @Test
    fun `LocatorFilterConfig default values`() {
        val config = LocatorFilterConfig()
        assertEquals("", config.hasText)
        assertEquals("", config.hasNotText)
    }

    @Test
    fun `LocatorFilterConfig with values`() {
        val config = LocatorFilterConfig(hasText = "hello", hasNotText = "world")
        assertEquals("hello", config.hasText)
        assertEquals("world", config.hasNotText)
    }

    @Test
    fun `LocatorOptionsConfig default values`() {
        val config = LocatorOptionsConfig()
        assertEquals("", config.hasText)
        assertEquals("", config.hasNotText)
    }

    @Test
    fun `LocatorOptionsConfig with values`() {
        val config = LocatorOptionsConfig(hasText = "foo", hasNotText = "bar")
        assertEquals("foo", config.hasText)
        assertEquals("bar", config.hasNotText)
    }
}
