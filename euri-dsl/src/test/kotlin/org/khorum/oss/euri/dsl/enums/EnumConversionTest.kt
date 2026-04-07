package org.khorum.oss.euri.dsl.enums

import com.microsoft.playwright.options.ColorScheme
import com.microsoft.playwright.options.LoadState
import com.microsoft.playwright.options.WaitUntilState
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EnumConversionTest {

    @Test
    fun `PageLoadState DomContentLoaded toPlaywright`() {
        assertEquals(LoadState.DOMCONTENTLOADED, PageLoadState.DomContentLoaded.toPlaywright())
    }

    @Test
    fun `PageLoadState Load toPlaywright`() {
        assertEquals(LoadState.LOAD, PageLoadState.Load.toPlaywright())
    }

    @Test
    fun `PageLoadState NetworkIdle toPlaywright`() {
        assertEquals(LoadState.NETWORKIDLE, PageLoadState.NetworkIdle.toPlaywright())
    }

    @Test
    fun `BrowserColorScheme Dark toPlaywright`() {
        assertEquals(ColorScheme.DARK, BrowserColorScheme.Dark.toPlaywright())
    }

    @Test
    fun `BrowserColorScheme Light toPlaywright`() {
        assertEquals(ColorScheme.LIGHT, BrowserColorScheme.Light.toPlaywright())
    }

    @Test
    fun `BrowserColorScheme NoPreference toPlaywright`() {
        assertEquals(ColorScheme.NO_PREFERENCE, BrowserColorScheme.NoPreference.toPlaywright())
    }

    @Test
    fun `BrowserColorScheme Null toPlaywright throws for missing constant`() {
        // ColorScheme.NULL does not exist in Playwright 1.50.0, so valueOf will throw
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            BrowserColorScheme.Null.toPlaywright()
        }
    }

    @Test
    fun `WaitUntil Commit toPlaywright`() {
        assertEquals(WaitUntilState.COMMIT, WaitUntil.Commit.toPlaywright())
    }

    @Test
    fun `WaitUntil DomContentLoaded toPlaywright`() {
        assertEquals(WaitUntilState.DOMCONTENTLOADED, WaitUntil.DomContentLoaded.toPlaywright())
    }

    @Test
    fun `WaitUntil Load toPlaywright`() {
        assertEquals(WaitUntilState.LOAD, WaitUntil.Load.toPlaywright())
    }

    @Test
    fun `WaitUntil NetworkIdle toPlaywright`() {
        assertEquals(WaitUntilState.NETWORKIDLE, WaitUntil.NetworkIdle.toPlaywright())
    }

    @Test
    fun `ScreenshotType Png toPlaywright`() {
        assertEquals("png", ScreenshotType.Png.toPlaywright())
    }

    @Test
    fun `ScreenshotType Jpeg toPlaywright`() {
        assertEquals("jpeg", ScreenshotType.Jpeg.toPlaywright())
    }
}
