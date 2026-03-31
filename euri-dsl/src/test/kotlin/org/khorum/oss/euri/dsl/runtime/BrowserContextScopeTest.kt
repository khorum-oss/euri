package org.khorum.oss.euri.dsl.runtime

import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.Page
import com.microsoft.playwright.options.Cookie
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.khorum.oss.euri.dsl.runtime.BrowserContextScope
import kotlin.test.assertEquals
import kotlin.test.assertSame

class BrowserContextScopeTest {

    private lateinit var context: BrowserContext
    private lateinit var scope: BrowserContextScope

    @BeforeEach
    fun setup() {
        context = mockk(relaxed = true)
        scope = BrowserContextScope(context)
    }

    @Test
    fun `addCookies delegates to context`() {
        val cookies = listOf(mockk<Cookie>())
        scope.addCookies(cookies)
        verify { context.addCookies(cookies) }
    }

    @Test
    fun `clearCookies delegates to context`() {
        scope.clearCookies()
        verify { context.clearCookies() }
    }

    @Test
    fun `cookies without urls delegates to context`() {
        val cookies = listOf(mockk<Cookie>())
        every { context.cookies() } returns cookies
        assertEquals(cookies, scope.cookies())
    }

    @Test
    fun `cookies with urls delegates to context`() {
        val cookies = listOf(mockk<Cookie>())
        every { context.cookies(listOf("https://example.com")) } returns cookies
        assertEquals(cookies, scope.cookies("https://example.com"))
    }

    @Test
    fun `grantPermissions delegates to context`() {
        scope.grantPermissions(listOf("geolocation"))
        verify { context.grantPermissions(listOf("geolocation")) }
    }

    @Test
    fun `grantPermissions with origin delegates to context`() {
        scope.grantPermissions(listOf("geolocation"), "https://example.com")
        verify { context.grantPermissions(listOf("geolocation"), any<BrowserContext.GrantPermissionsOptions>()) }
    }

    @Test
    fun `clearPermissions delegates to context`() {
        scope.clearPermissions()
        verify { context.clearPermissions() }
    }

    @Test
    fun `setGeolocation delegates to context`() {
        scope.setGeolocation(51.5, -0.1)
        verify { context.setGeolocation(any()) }
    }

    @Test
    fun `setOffline delegates to context`() {
        scope.setOffline(true)
        verify { context.setOffline(true) }
    }

    @Test
    fun `newPage creates page and applies block`() {
        val page = mockk<Page>(relaxed = true)
        every { context.newPage() } returns page
        var blockCalled = false
        scope.newPage { blockCalled = true }
        assertTrue(blockCalled)
        verify { context.newPage() }
    }

    @Test
    fun `pages delegates to context`() {
        val pages = listOf(mockk<Page>())
        every { context.pages() } returns pages
        assertEquals(pages, scope.pages())
    }

    @Test
    fun `onPage delegates to context`() {
        val handler: (Page) -> Unit = {}
        scope.onPage(handler)
        verify { context.onPage(any()) }
    }

    @Test
    fun `storageState without path delegates to context`() {
        every { context.storageState() } returns "{}"
        assertEquals("{}", scope.storageState())
    }

    @Test
    fun `storageState with path delegates to context`() {
        every { context.storageState(any<BrowserContext.StorageStateOptions>()) } returns "{}"
        assertEquals("{}", scope.storageState("/tmp/state.json"))
    }

    @Test
    fun `close delegates to context`() {
        scope.close()
        verify { context.close() }
    }

    @Test
    fun `raw returns underlying context`() {
        assertSame(context, scope.raw)
    }

    private fun assertTrue(value: Boolean) {
        kotlin.test.assertTrue(value)
    }
}
