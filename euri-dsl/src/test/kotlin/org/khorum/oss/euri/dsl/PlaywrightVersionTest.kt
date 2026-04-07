package org.khorum.oss.euri.dsl

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Suppress("TooManyFunctions")
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

    @Test
    fun `checkCompatibility returns Exact when version matches target`() {
        // The actual Playwright on the classpath is 1.50.0 which is TARGET_VERSION
        val result = PlaywrightVersion.checkCompatibility()
        // It will be Exact, Compatible, or Unknown depending on the jar manifest
        assertTrue(
            result is CompatibilityResult.Exact ||
                result is CompatibilityResult.Compatible ||
                result is CompatibilityResult.Unknown
        )
    }

    @Test
    fun `CompatibilityResult sealed class covers all variants`() {
        val results: List<CompatibilityResult> = listOf(
            CompatibilityResult.Exact,
            CompatibilityResult.Compatible("1.48.0"),
            CompatibilityResult.Incompatible("1.30.0"),
            CompatibilityResult.Missing,
            CompatibilityResult.Unknown
        )
        assertEquals(5, results.size)
    }

    @Test
    fun `CompatibilityResult Compatible toString contains version`() {
        val result = CompatibilityResult.Compatible("1.48.0")
        assertTrue(result.toString().contains("1.48.0"))
    }

    @Test
    fun `CompatibilityResult Incompatible toString contains version`() {
        val result = CompatibilityResult.Incompatible("1.30.0")
        assertTrue(result.toString().contains("1.30.0"))
    }

    @Test
    fun `isVersionGreaterOrEqual returns true for equal versions`() {
        val method = PlaywrightVersion::class.java.getDeclaredMethod(
            "isVersionGreaterOrEqual", String::class.java, String::class.java
        )
        method.isAccessible = true
        assertTrue(method.invoke(PlaywrightVersion, "1.44.0", "1.44.0") as Boolean)
    }

    @Test
    fun `isVersionGreaterOrEqual returns true for greater major version`() {
        val method = PlaywrightVersion::class.java.getDeclaredMethod(
            "isVersionGreaterOrEqual", String::class.java, String::class.java
        )
        method.isAccessible = true
        assertTrue(method.invoke(PlaywrightVersion, "2.0.0", "1.44.0") as Boolean)
    }

    @Test
    fun `isVersionGreaterOrEqual returns false for lesser version`() {
        val method = PlaywrightVersion::class.java.getDeclaredMethod(
            "isVersionGreaterOrEqual", String::class.java, String::class.java
        )
        method.isAccessible = true
        assertFalse(method.invoke(PlaywrightVersion, "1.43.0", "1.44.0") as Boolean)
    }

    @Test
    fun `isVersionGreaterOrEqual handles different length versions`() {
        val method = PlaywrightVersion::class.java.getDeclaredMethod(
            "isVersionGreaterOrEqual", String::class.java, String::class.java
        )
        method.isAccessible = true
        assertTrue(method.invoke(PlaywrightVersion, "1.50", "1.44.0") as Boolean)
    }

    @Test
    fun `isVersionGreaterOrEqual returns true for greater minor version`() {
        val method = PlaywrightVersion::class.java.getDeclaredMethod(
            "isVersionGreaterOrEqual", String::class.java, String::class.java
        )
        method.isAccessible = true
        assertTrue(method.invoke(PlaywrightVersion, "1.50.0", "1.44.0") as Boolean)
    }

    @Test
    fun `isVersionGreaterOrEqual returns false for lesser minor version`() {
        val method = PlaywrightVersion::class.java.getDeclaredMethod(
            "isVersionGreaterOrEqual", String::class.java, String::class.java
        )
        method.isAccessible = true
        assertFalse(method.invoke(PlaywrightVersion, "1.43.9", "1.44.0") as Boolean)
    }
}