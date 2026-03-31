package org.khorum.oss.euri.playwright.runtime

import com.microsoft.playwright.*
import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.options.LoadState
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.khorum.oss.euri.dsl.runtime.PageScope
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertSame

class PageScopeTest {

    private lateinit var page: Page
    private lateinit var scope: PageScope

    @BeforeEach
    fun setup() {
        page = mockk(relaxed = true)
        scope = PageScope(page)
    }

    // Navigation

    @Test
    fun `navigate with url only`() {
        val response = mockk<Response>()
        every { page.navigate("https://example.com") } returns response
        val result = scope.navigate("https://example.com")
        assertSame(response, result)
        verify { page.navigate("https://example.com") }
    }

    @Test
    fun `navigate with url and options block`() {
        val response = mockk<Response>()
        every { page.navigate(eq("https://example.com"), any<Page.NavigateOptions>()) } returns response
        val result = scope.navigate("https://example.com") { setTimeout(5000.0) }
        assertSame(response, result)
        verify { page.navigate("https://example.com", any<Page.NavigateOptions>()) }
    }

    @Test
    fun `reload without options`() {
        val response = mockk<Response>()
        every { page.reload(any<Page.ReloadOptions>()) } returns response
        val result = scope.reload()
        assertSame(response, result)
        verify { page.reload(any<Page.ReloadOptions>()) }
    }

    @Test
    fun `reload with options`() {
        val response = mockk<Response>()
        every { page.reload(any<Page.ReloadOptions>()) } returns response
        val result = scope.reload { setTimeout(5000.0) }
        assertSame(response, result)
        verify { page.reload(any<Page.ReloadOptions>()) }
    }

    @Test
    fun `goBack without options`() {
        every { page.goBack(any<Page.GoBackOptions>()) } returns null
        val result = scope.goBack()
        assertNull(result)
        verify { page.goBack(any<Page.GoBackOptions>()) }
    }

    @Test
    fun `goBack with options`() {
        val response = mockk<Response>()
        every { page.goBack(any<Page.GoBackOptions>()) } returns response
        val result = scope.goBack { setTimeout(5000.0) }
        assertSame(response, result)
        verify { page.goBack(any<Page.GoBackOptions>()) }
    }

    @Test
    fun `goForward without options`() {
        every { page.goForward(any<Page.GoForwardOptions>()) } returns null
        val result = scope.goForward()
        assertNull(result)
        verify { page.goForward(any<Page.GoForwardOptions>()) }
    }

    @Test
    fun `goForward with options`() {
        val response = mockk<Response>()
        every { page.goForward(any<Page.GoForwardOptions>()) } returns response
        val result = scope.goForward { setTimeout(5000.0) }
        assertSame(response, result)
        verify { page.goForward(any<Page.GoForwardOptions>()) }
    }

    // Content

    @Test
    fun `setContent without options`() {
        every { page.setContent(any(), any<Page.SetContentOptions>()) } just runs
        scope.setContent("<html></html>")
        verify { page.setContent("<html></html>", any<Page.SetContentOptions>()) }
    }

    @Test
    fun `setContent with options`() {
        every { page.setContent(any(), any<Page.SetContentOptions>()) } just runs
        scope.setContent("<html></html>") { setTimeout(5000.0) }
        verify { page.setContent("<html></html>", any<Page.SetContentOptions>()) }
    }

    @Test
    fun `content delegates to page`() {
        every { page.content() } returns "<html><body></body></html>"
        assertEquals("<html><body></body></html>", scope.content())
    }

    @Test
    fun `title delegates to page`() {
        every { page.title() } returns "Test Page"
        assertEquals("Test Page", scope.title())
    }

    @Test
    fun `url delegates to page`() {
        every { page.url() } returns "https://example.com"
        assertEquals("https://example.com", scope.url())
    }

    // Locators

    @Test
    fun `locator with selector only`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.locator("div.class") } returns locator
        val result = scope.locator("div.class")
        assertEquals(locator, result.raw)
    }

    @Test
    fun `locator with selector and options`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.locator(eq("div.class"), any<Page.LocatorOptions>()) } returns locator
        val result = scope.locator("div.class") { setHasText("hello") }
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByRole delegates to page`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.getByRole(AriaRole.BUTTON, any<Page.GetByRoleOptions>()) } returns locator
        val result = scope.getByRole(AriaRole.BUTTON)
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByRole with options`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.getByRole(AriaRole.BUTTON, any<Page.GetByRoleOptions>()) } returns locator
        val result = scope.getByRole(AriaRole.BUTTON) { setName("Submit") }
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByText delegates to page`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.getByText(eq("Hello"), any<Page.GetByTextOptions>()) } returns locator
        val result = scope.getByText("Hello")
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByText with exact`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.getByText(eq("Hello"), any<Page.GetByTextOptions>()) } returns locator
        val result = scope.getByText("Hello", exact = true)
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByLabel delegates to page`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.getByLabel(eq("Username"), any<Page.GetByLabelOptions>()) } returns locator
        val result = scope.getByLabel("Username")
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByLabel with exact`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.getByLabel(eq("Username"), any<Page.GetByLabelOptions>()) } returns locator
        scope.getByLabel("Username", exact = true)
        verify { page.getByLabel("Username", any<Page.GetByLabelOptions>()) }
    }

    @Test
    fun `getByPlaceholder delegates to page`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.getByPlaceholder(eq("Search"), any<Page.GetByPlaceholderOptions>()) } returns locator
        val result = scope.getByPlaceholder("Search")
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByPlaceholder with exact`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.getByPlaceholder(eq("Search"), any<Page.GetByPlaceholderOptions>()) } returns locator
        scope.getByPlaceholder("Search", exact = true)
        verify { page.getByPlaceholder("Search", any<Page.GetByPlaceholderOptions>()) }
    }

    @Test
    fun `getByTestId delegates to page`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.getByTestId("submit-btn") } returns locator
        val result = scope.getByTestId("submit-btn")
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByAltText delegates to page`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.getByAltText(eq("Logo"), any<Page.GetByAltTextOptions>()) } returns locator
        val result = scope.getByAltText("Logo")
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByAltText with exact`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.getByAltText(eq("Logo"), any<Page.GetByAltTextOptions>()) } returns locator
        scope.getByAltText("Logo", exact = true)
        verify { page.getByAltText("Logo", any<Page.GetByAltTextOptions>()) }
    }

    @Test
    fun `getByTitle delegates to page`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.getByTitle(eq("Help"), any<Page.GetByTitleOptions>()) } returns locator
        val result = scope.getByTitle("Help")
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByTitle with exact`() {
        val locator = mockk<Locator>(relaxed = true)
        every { page.getByTitle(eq("Help"), any<Page.GetByTitleOptions>()) } returns locator
        scope.getByTitle("Help", exact = true)
        verify { page.getByTitle("Help", any<Page.GetByTitleOptions>()) }
    }

    // Screenshots & PDF

    @Test
    fun `screenshot delegates to page`() {
        val bytes = byteArrayOf(1, 2, 3)
        every { page.screenshot(any<Page.ScreenshotOptions>()) } returns bytes
        val result = scope.screenshot { setFullPage(true) }
        assertEquals(bytes, result)
    }

    @Test
    fun `pdf delegates to page`() {
        val bytes = byteArrayOf(4, 5, 6)
        every { page.pdf(any<Page.PdfOptions>()) } returns bytes
        val result = scope.pdf { setLandscape(true) }
        assertEquals(bytes, result)
    }

    // Evaluation

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

    // Network

    @Test
    fun `route delegates to page`() {
        val handler: (Route) -> Unit = {}
        every { page.route(eq("**/*.png"), any()) } just runs
        scope.route("**/*.png", handler)
        verify { page.route(eq("**/*.png"), any()) }
    }

    @Test
    fun `unroute delegates to page`() {
        every { page.unroute("**/*.png") } just runs
        scope.unroute("**/*.png")
        verify { page.unroute("**/*.png") }
    }

    // Events

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

    // Waiting

    @Test
    fun `waitForSelector without options`() {
        val handle = mockk<ElementHandle>()
        every { page.waitForSelector(eq("div"), any<Page.WaitForSelectorOptions>()) } returns handle
        val result = scope.waitForSelector("div")
        assertSame(handle, result)
    }

    @Test
    fun `waitForSelector with options`() {
        every { page.waitForSelector(eq("div"), any<Page.WaitForSelectorOptions>()) } returns null
        val result = scope.waitForSelector("div") { setTimeout(5000.0) }
        assertNull(result)
    }

    @Test
    fun `waitForLoadState without state`() {
        every { page.waitForLoadState() } just runs
        scope.waitForLoadState()
        verify { page.waitForLoadState() }
    }

    @Test
    fun `waitForLoadState with state`() {
        every { page.waitForLoadState(LoadState.NETWORKIDLE) } just runs
        scope.waitForLoadState(LoadState.NETWORKIDLE)
        verify { page.waitForLoadState(LoadState.NETWORKIDLE) }
    }

    @Test
    fun `waitForURL without options`() {
        every { page.waitForURL(eq("https://example.com"), any<Page.WaitForURLOptions>()) } just runs
        scope.waitForURL("https://example.com")
        verify { page.waitForURL("https://example.com", any<Page.WaitForURLOptions>()) }
    }

    @Test
    fun `waitForURL with options`() {
        every { page.waitForURL(eq("https://example.com"), any<Page.WaitForURLOptions>()) } just runs
        scope.waitForURL("https://example.com") { setTimeout(5000.0) }
        verify { page.waitForURL("https://example.com", any<Page.WaitForURLOptions>()) }
    }

    @Test
    fun `waitForTimeout delegates to page`() {
        every { page.waitForTimeout(1000.0) } just runs
        scope.waitForTimeout(1000.0)
        verify { page.waitForTimeout(1000.0) }
    }

    // Frames

    @Test
    fun `frame returns FrameScope when frame exists`() {
        val frame = mockk<Frame>(relaxed = true)
        every { page.frame("myframe") } returns frame
        val result = scope.frame("myframe")
        assertEquals(frame, result?.raw)
    }

    @Test
    fun `frame returns null when frame does not exist`() {
        every { page.frame("missing") } returns null
        assertNull(scope.frame("missing"))
    }

    @Test
    fun `frameLocator delegates to page`() {
        val frameLocator = mockk<FrameLocator>()
        every { page.frameLocator("iframe") } returns frameLocator
        assertSame(frameLocator, scope.frameLocator("iframe"))
    }

    // Drag & Drop

    @Test
    fun `dragAndDrop without options`() {
        every { page.dragAndDrop(eq("#source"), eq("#target"), any<Page.DragAndDropOptions>()) } just runs
        scope.dragAndDrop("#source", "#target")
        verify { page.dragAndDrop("#source", "#target", any<Page.DragAndDropOptions>()) }
    }

    @Test
    fun `dragAndDrop with options`() {
        every { page.dragAndDrop(eq("#source"), eq("#target"), any<Page.DragAndDropOptions>()) } just runs
        scope.dragAndDrop("#source", "#target") { setSourcePosition(10.0, 20.0) }
        verify { page.dragAndDrop("#source", "#target", any<Page.DragAndDropOptions>()) }
    }

    // Keyboard, Mouse, Touchscreen

    @Test
    fun `keyboard delegates to page`() {
        val keyboard = mockk<Keyboard>()
        every { page.keyboard() } returns keyboard
        assertSame(keyboard, scope.keyboard)
    }

    @Test
    fun `mouse delegates to page`() {
        val mouse = mockk<Mouse>()
        every { page.mouse() } returns mouse
        assertSame(mouse, scope.mouse)
    }

    @Test
    fun `touchscreen delegates to page`() {
        val touchscreen = mockk<Touchscreen>()
        every { page.touchscreen() } returns touchscreen
        assertSame(touchscreen, scope.touchscreen)
    }

    // Close

    @Test
    fun `close without options`() {
        every { page.close(any<Page.CloseOptions>()) } just runs
        scope.close()
        verify { page.close(any<Page.CloseOptions>()) }
    }

    @Test
    fun `close with options`() {
        every { page.close(any<Page.CloseOptions>()) } just runs
        scope.close { setRunBeforeUnload(true) }
        verify { page.close(any<Page.CloseOptions>()) }
    }

    // Raw

    @Test
    fun `raw returns underlying page`() {
        assertSame(page, scope.raw)
    }
}
