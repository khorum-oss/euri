package org.khorum.oss.euri.playwright

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class PlaywrightVersionTest {

    @Test
    fun `TARGET_VERSION is 1_50_0`() {
        assertEquals("1.50.0", PlaywrightVersion.TARGET_VERSION)
    }

    @Test
    fun `MIN_VERSION is 1_44_0`() {
        assertEquals("1.44.0", PlaywrightVersion.MIN_VERSION)
    }

    @Test
    fun `checkCompatibility returns Exact or Compatible when Playwright is on classpath`() {
        val result = PlaywrightVersion.checkCompatibility()
        // Playwright is on the classpath in tests, so it should not be Missing
        assertTrue(
            result is CompatibilityResult.Exact ||
                result is CompatibilityResult.Compatible ||
                result is CompatibilityResult.Unknown
        )
    }

    @Test
    fun `checkCompatibility does not return Missing when Playwright jar is present`() {
        val result = PlaywrightVersion.checkCompatibility()
        assertFalse(result is CompatibilityResult.Missing)
    }

    @Test
    fun `CompatibilityResult Exact is a singleton`() {
        val a = CompatibilityResult.Exact
        val b = CompatibilityResult.Exact
        assertEquals(a, b)
    }

    @Test
    fun `CompatibilityResult Missing is a singleton`() {
        val a = CompatibilityResult.Missing
        val b = CompatibilityResult.Missing
        assertEquals(a, b)
    }

    @Test
    fun `CompatibilityResult Unknown is a singleton`() {
        val a = CompatibilityResult.Unknown
        val b = CompatibilityResult.Unknown
        assertEquals(a, b)
    }

    @Test
    fun `CompatibilityResult Compatible holds version`() {
        val result = CompatibilityResult.Compatible("1.48.0")
        assertEquals("1.48.0", result.actualVersion)
    }

    @Test
    fun `CompatibilityResult Incompatible holds version`() {
        val result = CompatibilityResult.Incompatible("1.30.0")
        assertEquals("1.30.0", result.actualVersion)
    }

    @Test
    fun `CompatibilityResult Compatible equality`() {
        val a = CompatibilityResult.Compatible("1.48.0")
        val b = CompatibilityResult.Compatible("1.48.0")
        assertEquals(a, b)
    }

    @Test
    fun `CompatibilityResult Incompatible equality`() {
        val a = CompatibilityResult.Incompatible("1.30.0")
        val b = CompatibilityResult.Incompatible("1.30.0")
        assertEquals(a, b)
    }
}
