package org.khorum.oss.euri.dsl.runtime

import com.microsoft.playwright.Frame
import com.microsoft.playwright.Locator
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.khorum.oss.euri.dsl.enums.Role
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
    fun `locator creates scope and processes`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.locator("#test") } returns locator
        scope.locator("#test") { }
        verify { frame.locator("#test") }
    }

    @Test
    fun `getByRole delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByRole(any(), any<Frame.GetByRoleOptions>()) } returns locator
        scope.getByRole(Role.Button) { }
        verify { frame.getByRole(any(), any<Frame.GetByRoleOptions>()) }
    }

    @Test
    fun `getByRole with all options delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByRole(any(), any<Frame.GetByRoleOptions>()) } returns locator
        scope.getByRole(
            role = Role.Heading,
            checked = true,
            disabled = false,
            exact = true,
            expanded = false,
            includeHidden = true,
            level = 2,
            name = "Title",
            pressed = false,
            selected = true
        ) { }
        verify { frame.getByRole(any(), any<Frame.GetByRoleOptions>()) }
    }

    @Test
    fun `getByText delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByText("Hello", any<Frame.GetByTextOptions>()) } returns locator
        scope.getByText("Hello") { }
        verify { frame.getByText("Hello", any<Frame.GetByTextOptions>()) }
    }

    @Test
    fun `getByText with exact delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByText("Hello", any<Frame.GetByTextOptions>()) } returns locator
        scope.getByText("Hello", exact = true) { }
        verify { frame.getByText("Hello", any<Frame.GetByTextOptions>()) }
    }

    @Test
    fun `getByLabel delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByLabel("Email", any<Frame.GetByLabelOptions>()) } returns locator
        scope.getByLabel("Email") { }
        verify { frame.getByLabel("Email", any<Frame.GetByLabelOptions>()) }
    }

    @Test
    fun `getByPlaceholder delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByPlaceholder("Enter", any<Frame.GetByPlaceholderOptions>()) } returns locator
        scope.getByPlaceholder("Enter") { }
        verify { frame.getByPlaceholder("Enter", any<Frame.GetByPlaceholderOptions>()) }
    }

    @Test
    fun `getByTestId delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByTestId("submit") } returns locator
        scope.getByTestId("submit") { }
        verify { frame.getByTestId("submit") }
    }

    @Test
    fun `getByAltText delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByAltText("Logo", any<Frame.GetByAltTextOptions>()) } returns locator
        scope.getByAltText("Logo") { }
        verify { frame.getByAltText("Logo", any<Frame.GetByAltTextOptions>()) }
    }

    @Test
    fun `getByTitle delegates to frame`() {
        val locator = mockk<Locator>(relaxed = true)
        every { frame.getByTitle("Main", any<Frame.GetByTitleOptions>()) } returns locator
        scope.getByTitle("Main") { }
        verify { frame.getByTitle("Main", any<Frame.GetByTitleOptions>()) }
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
        every { frame.url() } returns "http://example.com"
        assertEquals("http://example.com", scope.url())
    }

    @Test
    fun `name delegates to frame`() {
        every { frame.name() } returns "main"
        assertEquals("main", scope.name())
    }

    @Test
    fun `raw returns underlying frame`() {
        assertSame(frame, scope.raw)
    }
}
