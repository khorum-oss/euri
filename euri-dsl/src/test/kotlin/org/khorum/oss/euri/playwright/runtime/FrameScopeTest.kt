package org.khorum.oss.euri.playwright.runtime

import com.microsoft.playwright.Frame
import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.AriaRole
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.khorum.oss.euri.dsl.runtime.FrameScope
import kotlin.test.assertEquals
import kotlin.test.assertSame

class FrameScopeTest {

    private lateinit var frame: Frame
    private lateinit var scope: FrameScope

    @BeforeEach
    fun setup() {
        frame = mockk(relaxed = true)
        scope = FrameScope(frame)
    }

    @Test
    fun `locator delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.locator("div") } returns locator
        val result = scope.locator("div")
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByRole delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByRole(AriaRole.BUTTON, any<Frame.GetByRoleOptions>()) } returns locator
        val result = scope.getByRole(AriaRole.BUTTON)
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByRole with options`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByRole(AriaRole.BUTTON, any<Frame.GetByRoleOptions>()) } returns locator
        scope.getByRole(AriaRole.BUTTON) { setName("Submit") }
        verify { frame.getByRole(AriaRole.BUTTON, any<Frame.GetByRoleOptions>()) }
    }

    @Test
    fun `getByText delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByText(eq("Hello"), any<Frame.GetByTextOptions>()) } returns locator
        val result = scope.getByText("Hello")
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByText with exact`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByText(eq("Hello"), any<Frame.GetByTextOptions>()) } returns locator
        scope.getByText("Hello", exact = true)
        verify { frame.getByText("Hello", any<Frame.GetByTextOptions>()) }
    }

    @Test
    fun `getByLabel delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByLabel(eq("Username"), any<Frame.GetByLabelOptions>()) } returns locator
        val result = scope.getByLabel("Username")
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByLabel with exact`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByLabel(eq("Username"), any<Frame.GetByLabelOptions>()) } returns locator
        scope.getByLabel("Username", exact = true)
        verify { frame.getByLabel("Username", any<Frame.GetByLabelOptions>()) }
    }

    @Test
    fun `getByPlaceholder delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByPlaceholder(eq("Search"), any<Frame.GetByPlaceholderOptions>()) } returns locator
        val result = scope.getByPlaceholder("Search")
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByPlaceholder with exact`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByPlaceholder(eq("Search"), any<Frame.GetByPlaceholderOptions>()) } returns locator
        scope.getByPlaceholder("Search", exact = true)
        verify { frame.getByPlaceholder("Search", any<Frame.GetByPlaceholderOptions>()) }
    }

    @Test
    fun `getByTestId delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByTestId("test-id") } returns locator
        val result = scope.getByTestId("test-id")
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByAltText delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByAltText(eq("Logo"), any<Frame.GetByAltTextOptions>()) } returns locator
        val result = scope.getByAltText("Logo")
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByAltText with exact`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByAltText(eq("Logo"), any<Frame.GetByAltTextOptions>()) } returns locator
        scope.getByAltText("Logo", exact = true)
        verify { frame.getByAltText("Logo", any<Frame.GetByAltTextOptions>()) }
    }

    @Test
    fun `getByTitle delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByTitle(eq("Help"), any<Frame.GetByTitleOptions>()) } returns locator
        val result = scope.getByTitle("Help")
        assertEquals(locator, result.raw)
    }

    @Test
    fun `getByTitle with exact`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByTitle(eq("Help"), any<Frame.GetByTitleOptions>()) } returns locator
        scope.getByTitle("Help", exact = true)
        verify { frame.getByTitle("Help", any<Frame.GetByTitleOptions>()) }
    }

    @Test
    fun `evaluate delegates to frame`() {
        every { frame.evaluate("1 + 1") } returns 2
        assertEquals(2, scope.evaluate("1 + 1"))
    }

    @Test
    fun `evaluate with arg delegates to frame`() {
        every { frame.evaluate("x => x * 2", 5) } returns 10
        assertEquals(10, scope.evaluate("x => x * 2", 5))
    }

    @Test
    fun `content delegates to frame`() {
        every { frame.content() } returns "<html></html>"
        assertEquals("<html></html>", scope.content())
    }

    @Test
    fun `url delegates to frame`() {
        every { frame.url() } returns "https://example.com"
        assertEquals("https://example.com", scope.url())
    }

    @Test
    fun `name delegates to frame`() {
        every { frame.name() } returns "myframe"
        assertEquals("myframe", scope.name())
    }

    @Test
    fun `raw returns underlying frame`() {
        assertSame(frame, scope.raw)
    }
}
