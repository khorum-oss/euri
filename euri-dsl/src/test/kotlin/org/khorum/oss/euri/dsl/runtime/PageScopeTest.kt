package org.khorum.oss.euri.dsl.runtime

import com.microsoft.playwright.*
import com.microsoft.playwright.options.LoadState
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.khorum.oss.euri.dsl.enums.Role
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

class PageScopeTest {

    private lateinit var page: Page
    private lateinit var scope: PageScope

    @BeforeEach
    fun setup() {
        page = mockk(relaxed = true)
        scope = PageScope(page)
    }

    @Nested
    inner class Navigation {

        @Test
        fun `navigate delegates to page`() {
            scope.navigate("http://example.com")
            verify { page.navigate("http://example.com") }
        }

        @Test
        fun `navigate with options delegates to page`() {
            scope.navigate("http://example.com") {
                setTimeout(5000.0)
            }
            verify { page.navigate("http://example.com", any<Page.NavigateOptions>()) }
        }

        @Test
        fun `reload delegates to page`() {
            scope.reload()
            verify { page.reload(any<Page.ReloadOptions>()) }
        }

        @Test
        fun `reload with options delegates to page`() {
            scope.reload { setTimeout(5000.0) }
            verify { page.reload(any<Page.ReloadOptions>()) }
        }

        @Test
        fun `goBack delegates to page`() {
            scope.goBack()
            verify { page.goBack(any<Page.GoBackOptions>()) }
        }

        @Test
        fun `goBack with options delegates to page`() {
            scope.goBack { setTimeout(5000.0) }
            verify { page.goBack(any<Page.GoBackOptions>()) }
        }

        @Test
        fun `goForward delegates to page`() {
            scope.goForward()
            verify { page.goForward(any<Page.GoForwardOptions>()) }
        }

        @Test
        fun `goForward with options delegates to page`() {
            scope.goForward { setTimeout(5000.0) }
            verify { page.goForward(any<Page.GoForwardOptions>()) }
        }
    }

    @Nested
    inner class Content {

        @Test
        fun `setContent delegates to page`() {
            scope.setContent("<h1>Hello</h1>")
            verify { page.setContent("<h1>Hello</h1>", any<Page.SetContentOptions>()) }
        }

        @Test
        fun `setContent with options delegates to page`() {
            scope.setContent("<h1>Hello</h1>") { setTimeout(5000.0) }
            verify { page.setContent("<h1>Hello</h1>", any<Page.SetContentOptions>()) }
        }

        @Test
        fun `content delegates to page`() {
            every { page.content() } returns "<html></html>"
            assertEquals("<html></html>", scope.content())
        }

        @Test
        fun `title delegates to page`() {
            every { page.title() } returns "Test Page"
            assertEquals("Test Page", scope.title())
        }

        @Test
        fun `url delegates to page`() {
            every { page.url() } returns "http://example.com"
            assertEquals("http://example.com", scope.url())
        }
    }

    @Nested
    inner class Locators {

        @Test
        fun `locator creates scope and processes`() {
            val locator = mockk<Locator>(relaxed = true)
            every { page.locator("#test") } returns locator
            scope.locator("#test") { }
            verify { page.locator("#test") }
        }

        @Test
        fun `locator with options creates scope and processes`() {
            val locator = mockk<Locator>(relaxed = true)
            val options = Page.LocatorOptions()
            every { page.locator("#test", options) } returns locator
            scope.locator("#test", options) { }
            verify { page.locator("#test", options) }
        }

        @Test
        fun `getByRole delegates to page`() {
            val locator = mockk<Locator>(relaxed = true)
            every { page.getByRole(any(), any<Page.GetByRoleOptions>()) } returns locator
            scope.getByRole(Role.Button) { }
            verify { page.getByRole(any(), any<Page.GetByRoleOptions>()) }
        }

        @Test
        fun `getByRole with all options delegates to page`() {
            val locator = mockk<Locator>(relaxed = true)
            every { page.getByRole(any(), any<Page.GetByRoleOptions>()) } returns locator
            scope.getByRole(
                role = Role.Button,
                checked = true,
                disabled = false,
                exact = true,
                expanded = false,
                includeHidden = true,
                level = 2,
                name = "Submit",
                pressed = false,
                selected = true
            ) { }
            verify { page.getByRole(any(), any<Page.GetByRoleOptions>()) }
        }

        @Test
        fun `getByText delegates to page`() {
            val locator = mockk<Locator>(relaxed = true)
            every { page.getByText("Hello", any<Page.GetByTextOptions>()) } returns locator
            scope.getByText("Hello") { }
            verify { page.getByText("Hello", any<Page.GetByTextOptions>()) }
        }

        @Test
        fun `getByText with exact delegates to page`() {
            val locator = mockk<Locator>(relaxed = true)
            every { page.getByText("Hello", any<Page.GetByTextOptions>()) } returns locator
            scope.getByText("Hello", exact = true) { }
            verify { page.getByText("Hello", any<Page.GetByTextOptions>()) }
        }

        @Test
        fun `getByLabel delegates to page`() {
            val locator = mockk<Locator>(relaxed = true)
            every { page.getByLabel("Email", any<Page.GetByLabelOptions>()) } returns locator
            scope.getByLabel("Email") { }
            verify { page.getByLabel("Email", any<Page.GetByLabelOptions>()) }
        }

        @Test
        fun `getByPlaceholder delegates to page`() {
            val locator = mockk<Locator>(relaxed = true)
            every { page.getByPlaceholder("Enter email", any<Page.GetByPlaceholderOptions>()) } returns locator
            scope.getByPlaceholder("Enter email") { }
            verify { page.getByPlaceholder("Enter email", any<Page.GetByPlaceholderOptions>()) }
        }

        @Test
        fun `getByTestId delegates to page`() {
            val locator = mockk<Locator>(relaxed = true)
            every { page.getByTestId("submit-btn") } returns locator
            scope.getByTestId("submit-btn") { }
            verify { page.getByTestId("submit-btn") }
        }

        @Test
        fun `getByAltText delegates to page`() {
            val locator = mockk<Locator>(relaxed = true)
            every { page.getByAltText("Logo", any<Page.GetByAltTextOptions>()) } returns locator
            scope.getByAltText("Logo") { }
            verify { page.getByAltText("Logo", any<Page.GetByAltTextOptions>()) }
        }

        @Test
        fun `getByTitle delegates to page`() {
            val locator = mockk<Locator>(relaxed = true)
            every { page.getByTitle("Main", any<Page.GetByTitleOptions>()) } returns locator
            scope.getByTitle("Main") { }
            verify { page.getByTitle("Main", any<Page.GetByTitleOptions>()) }
        }
    }

    @Nested
    inner class Screenshots {

        @Test
        fun `screenshot delegates to page`() {
            val bytes = byteArrayOf(1, 2, 3)
            every { page.screenshot(any<Page.ScreenshotOptions>()) } returns bytes
            val result = scope.screenshot { }
            assertSame(bytes, result)
        }
    }

    @Nested
    inner class Pdf {

        @Test
        fun `pdf delegates to page`() {
            val bytes = byteArrayOf(1, 2, 3)
            every { page.pdf(any<Page.PdfOptions>()) } returns bytes
            val result = scope.pdf { }
            assertSame(bytes, result)
        }
    }

    @Nested
    inner class Evaluation {

        @Test
        fun `evaluate delegates to page`() {
            every { page.evaluate("1 + 1") } returns 2
            assertEquals(2, scope.evaluate("1 + 1"))
        }

        @Test
        fun `evaluate with arg delegates to page`() {
            every { page.evaluate("x => x * 2", 5) } returns 10
            assertEquals(10, scope.evaluate("x => x * 2", 5))
        }
    }

    @Nested
    inner class Network {

        @Test
        fun `route delegates to page`() {
            val handler: (Route) -> Unit = {}
            scope.route("**/api/*", handler)
            verify { page.route("**/api/*", any()) }
        }

        @Test
        fun `unroute delegates to page`() {
            scope.unroute("**/api/*")
            verify { page.unroute("**/api/*") }
        }
    }

    @Nested
    inner class Events {

        @Test
        fun `onRequest delegates to page`() {
            val handler: (Request) -> Unit = {}
            scope.onRequest(handler)
            verify { page.onRequest(any()) }
        }

        @Test
        fun `onResponse delegates to page`() {
            val handler: (Response) -> Unit = {}
            scope.onResponse(handler)
            verify { page.onResponse(any()) }
        }

        @Test
        fun `onDialog delegates to page`() {
            val handler: (Dialog) -> Unit = {}
            scope.onDialog(handler)
            verify { page.onDialog(any()) }
        }

        @Test
        fun `onConsoleMessage delegates to page`() {
            val handler: (ConsoleMessage) -> Unit = {}
            scope.onConsoleMessage(handler)
            verify { page.onConsoleMessage(any()) }
        }
    }

    @Nested
    inner class Waiting {

        @Test
        fun `waitForSelector delegates to page`() {
            scope.waitForSelector("#test")
            verify { page.waitForSelector("#test", any<Page.WaitForSelectorOptions>()) }
        }

        @Test
        fun `waitForSelector with options delegates to page`() {
            scope.waitForSelector("#test") { setTimeout(5000.0) }
            verify { page.waitForSelector("#test", any<Page.WaitForSelectorOptions>()) }
        }

        @Test
        fun `waitForLoadState without state delegates to page`() {
            scope.waitForLoadState()
            verify { page.waitForLoadState() }
        }

        @Test
        fun `waitForLoadState with state delegates to page`() {
            scope.waitForLoadState(LoadState.NETWORKIDLE)
            verify { page.waitForLoadState(LoadState.NETWORKIDLE) }
        }

        @Test
        fun `waitForURL delegates to page`() {
            scope.waitForURL("http://example.com")
            verify { page.waitForURL("http://example.com", any<Page.WaitForURLOptions>()) }
        }

        @Test
        fun `waitForURL with options delegates to page`() {
            scope.waitForURL("http://example.com") { setTimeout(5000.0) }
            verify { page.waitForURL("http://example.com", any<Page.WaitForURLOptions>()) }
        }

        @Test
        fun `waitForTimeout delegates to page`() {
            scope.waitForTimeout(1000.0)
            verify { page.waitForTimeout(1000.0) }
        }
    }

    @Nested
    inner class Frames {

        @Test
        fun `frame with existing frame applies block`() {
            val frame = mockk<Frame>(relaxed = true)
            every { page.frame("main") } returns frame
            var blockCalled = false
            scope.frame("main") { blockCalled = true }
            assertTrue(blockCalled)
        }

        @Test
        fun `frame with non-existing frame does not apply block`() {
            every { page.frame("missing") } returns null
            var blockCalled = false
            scope.frame("missing") { blockCalled = true }
            assertTrue(!blockCalled)
        }

        @Test
        fun `frameLocator delegates to page`() {
            val frameLocator = mockk<FrameLocator>()
            every { page.frameLocator("#iframe") } returns frameLocator
            assertSame(frameLocator, scope.frameLocator("#iframe"))
        }
    }

    @Nested
    inner class DragAndDrop {

        @Test
        fun `dragAndDrop delegates to page`() {
            scope.dragAndDrop("#source", "#target")
            verify { page.dragAndDrop("#source", "#target", any<Page.DragAndDropOptions>()) }
        }

        @Test
        fun `dragAndDrop with options delegates to page`() {
            scope.dragAndDrop("#source", "#target") { }
            verify { page.dragAndDrop("#source", "#target", any<Page.DragAndDropOptions>()) }
        }
    }

    @Nested
    inner class KeyboardMouseTouchscreen {

        @Test
        fun `keyboard returns page keyboard`() {
            val keyboard = mockk<Keyboard>()
            every { page.keyboard() } returns keyboard
            assertSame(keyboard, scope.keyboard)
        }

        @Test
        fun `mouse returns page mouse`() {
            val mouse = mockk<Mouse>()
            every { page.mouse() } returns mouse
            assertSame(mouse, scope.mouse)
        }

        @Test
        fun `touchscreen returns page touchscreen`() {
            val touchscreen = mockk<Touchscreen>()
            every { page.touchscreen() } returns touchscreen
            assertSame(touchscreen, scope.touchscreen)
        }
    }

    @Nested
    inner class Close {

        @Test
        fun `close delegates to page`() {
            scope.close()
            verify { page.close(any<Page.CloseOptions>()) }
        }

        @Test
        fun `close with options delegates to page`() {
            scope.close { }
            verify { page.close(any<Page.CloseOptions>()) }
        }
    }

    @Test
    fun `raw returns underlying page`() {
        assertSame(page, scope.raw)
    }
}
