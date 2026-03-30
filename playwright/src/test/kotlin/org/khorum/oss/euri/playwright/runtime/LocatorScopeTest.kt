package org.khorum.oss.euri.playwright.runtime

import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.AriaRole
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.regex.Pattern
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

class LocatorScopeTest {

    private lateinit var locator: Locator
    private lateinit var scope: LocatorScope

    @BeforeEach
    fun setup() {
        locator = mockk(relaxed = true)
        scope = LocatorScope(locator)
    }

    // Actions

    @Test
    fun `click without options`() {
        scope.click()
        verify { locator.click(any<Locator.ClickOptions>()) }
    }

    @Test
    fun `click with options`() {
        scope.click { setClickCount(2) }
        verify { locator.click(any<Locator.ClickOptions>()) }
    }

    @Test
    fun `dblclick without options`() {
        scope.dblclick()
        verify { locator.dblclick(any<Locator.DblclickOptions>()) }
    }

    @Test
    fun `dblclick with options`() {
        scope.dblclick { setForce(true) }
        verify { locator.dblclick(any<Locator.DblclickOptions>()) }
    }

    @Test
    fun `fill without options`() {
        scope.fill("text")
        verify { locator.fill("text", any<Locator.FillOptions>()) }
    }

    @Test
    fun `fill with options`() {
        scope.fill("text") { setForce(true) }
        verify { locator.fill("text", any<Locator.FillOptions>()) }
    }

    @Test
    fun `press without options`() {
        scope.press("Enter")
        verify { locator.press("Enter", any<Locator.PressOptions>()) }
    }

    @Test
    fun `press with options`() {
        scope.press("Enter") { setDelay(100.0) }
        verify { locator.press("Enter", any<Locator.PressOptions>()) }
    }

    @Test
    fun `pressSequentially without options`() {
        scope.pressSequentially("hello")
        verify { locator.pressSequentially("hello", any<Locator.PressSequentiallyOptions>()) }
    }

    @Test
    fun `pressSequentially with options`() {
        scope.pressSequentially("hello") { setDelay(50.0) }
        verify { locator.pressSequentially("hello", any<Locator.PressSequentiallyOptions>()) }
    }

    @Test
    fun `check without options`() {
        scope.check()
        verify { locator.check(any<Locator.CheckOptions>()) }
    }

    @Test
    fun `check with options`() {
        scope.check { setForce(true) }
        verify { locator.check(any<Locator.CheckOptions>()) }
    }

    @Test
    fun `uncheck without options`() {
        scope.uncheck()
        verify { locator.uncheck(any<Locator.UncheckOptions>()) }
    }

    @Test
    fun `uncheck with options`() {
        scope.uncheck { setForce(true) }
        verify { locator.uncheck(any<Locator.UncheckOptions>()) }
    }

    @Test
    fun `hover without options`() {
        scope.hover()
        verify { locator.hover(any<Locator.HoverOptions>()) }
    }

    @Test
    fun `hover with options`() {
        scope.hover { setForce(true) }
        verify { locator.hover(any<Locator.HoverOptions>()) }
    }

    @Test
    fun `focus delegates`() {
        scope.focus()
        verify { locator.focus() }
    }

    @Test
    fun `blur without options`() {
        scope.blur()
        verify { locator.blur(any<Locator.BlurOptions>()) }
    }

    @Test
    fun `blur with options`() {
        scope.blur { setTimeout(5000.0) }
        verify { locator.blur(any<Locator.BlurOptions>()) }
    }

    @Test
    fun `clear without options`() {
        scope.clear()
        verify { locator.clear(any<Locator.ClearOptions>()) }
    }

    @Test
    fun `clear with options`() {
        scope.clear { setForce(true) }
        verify { locator.clear(any<Locator.ClearOptions>()) }
    }

    @Test
    fun `selectOption with single value`() {
        every { locator.selectOption("value1") } returns listOf("value1")
        val result = scope.selectOption("value1")
        assertEquals(listOf("value1"), result)
    }

    @Test
    fun `selectOption with multiple values`() {
        every { locator.selectOption(arrayOf("a", "b")) } returns listOf("a", "b")
        val result = scope.selectOption(arrayOf("a", "b"))
        assertEquals(listOf("a", "b"), result)
    }

    @Test
    fun `setInputFiles delegates`() {
        val files = arrayOf(java.nio.file.Paths.get("/tmp/file.txt"))
        scope.setInputFiles(files)
        verify { locator.setInputFiles(files) }
    }

    @Test
    fun `dragTo without options`() {
        val targetLocator = mockk<Locator>(relaxed = true)
        val targetScope = LocatorScope(targetLocator)
        scope.dragTo(targetScope)
        verify { locator.dragTo(targetLocator, any<Locator.DragToOptions>()) }
    }

    @Test
    fun `dragTo with options`() {
        val targetLocator = mockk<Locator>(relaxed = true)
        val targetScope = LocatorScope(targetLocator)
        scope.dragTo(targetScope) { setForce(true) }
        verify { locator.dragTo(targetLocator, any<Locator.DragToOptions>()) }
    }

    // Chaining

    @Test
    fun `locator chaining`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.locator("span") } returns child
        val result = scope.locator("span")
        assertEquals(child, result.raw)
    }

    @Test
    fun `filter returns new LocatorScope`() {
        val filtered = mockk<Locator>(relaxed = true)
        every { locator.filter(any<Locator.FilterOptions>()) } returns filtered
        val result = scope.filter { setHasText("hello") }
        assertEquals(filtered, result.raw)
    }

    @Test
    fun `first returns new LocatorScope`() {
        val first = mockk<Locator>(relaxed = true)
        every { locator.first() } returns first
        assertEquals(first, scope.first().raw)
    }

    @Test
    fun `last returns new LocatorScope`() {
        val last = mockk<Locator>(relaxed = true)
        every { locator.last() } returns last
        assertEquals(last, scope.last().raw)
    }

    @Test
    fun `nth returns new LocatorScope`() {
        val nth = mockk<Locator>(relaxed = true)
        every { locator.nth(2) } returns nth
        assertEquals(nth, scope.nth(2).raw)
    }

    @Test
    fun `and returns new LocatorScope`() {
        val other = mockk<Locator>(relaxed = true)
        val combined = mockk<Locator>(relaxed = true)
        every { locator.and(other) } returns combined
        val result = scope.and(LocatorScope(other))
        assertEquals(combined, result.raw)
    }

    @Test
    fun `or returns new LocatorScope`() {
        val other = mockk<Locator>(relaxed = true)
        val combined = mockk<Locator>(relaxed = true)
        every { locator.or(other) } returns combined
        val result = scope.or(LocatorScope(other))
        assertEquals(combined, result.raw)
    }

    @Test
    fun `getByRole delegates to locator`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByRole(AriaRole.BUTTON, any<Locator.GetByRoleOptions>()) } returns child
        val result = scope.getByRole(AriaRole.BUTTON)
        assertEquals(child, result.raw)
    }

    @Test
    fun `getByRole with options`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByRole(AriaRole.BUTTON, any<Locator.GetByRoleOptions>()) } returns child
        scope.getByRole(AriaRole.BUTTON) { setName("Submit") }
        verify { locator.getByRole(AriaRole.BUTTON, any<Locator.GetByRoleOptions>()) }
    }

    @Test
    fun `getByText with string`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByText(eq("Hello"), any<Locator.GetByTextOptions>()) } returns child
        val result = scope.getByText("Hello")
        assertEquals(child, result.raw)
    }

    @Test
    fun `getByText with exact`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByText(eq("Hello"), any<Locator.GetByTextOptions>()) } returns child
        scope.getByText("Hello", exact = true)
        verify { locator.getByText("Hello", any<Locator.GetByTextOptions>()) }
    }

    @Test
    fun `getByText with Pattern`() {
        val child = mockk<Locator>(relaxed = true)
        val pattern = Pattern.compile("hello.*")
        every { locator.getByText(pattern) } returns child
        val result = scope.getByText(pattern)
        assertEquals(child, result.raw)
    }

    @Test
    fun `getByLabel delegates`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByLabel(eq("Username"), any<Locator.GetByLabelOptions>()) } returns child
        val result = scope.getByLabel("Username")
        assertEquals(child, result.raw)
    }

    @Test
    fun `getByLabel with exact`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByLabel(eq("Username"), any<Locator.GetByLabelOptions>()) } returns child
        scope.getByLabel("Username", exact = true)
        verify { locator.getByLabel("Username", any<Locator.GetByLabelOptions>()) }
    }

    @Test
    fun `getByPlaceholder delegates`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByPlaceholder(eq("Search"), any<Locator.GetByPlaceholderOptions>()) } returns child
        val result = scope.getByPlaceholder("Search")
        assertEquals(child, result.raw)
    }

    @Test
    fun `getByPlaceholder with exact`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByPlaceholder(eq("Search"), any<Locator.GetByPlaceholderOptions>()) } returns child
        scope.getByPlaceholder("Search", exact = true)
        verify { locator.getByPlaceholder("Search", any<Locator.GetByPlaceholderOptions>()) }
    }

    @Test
    fun `getByTestId delegates`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByTestId("test-id") } returns child
        val result = scope.getByTestId("test-id")
        assertEquals(child, result.raw)
    }

    @Test
    fun `getByAltText delegates`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByAltText(eq("Logo"), any<Locator.GetByAltTextOptions>()) } returns child
        val result = scope.getByAltText("Logo")
        assertEquals(child, result.raw)
    }

    @Test
    fun `getByAltText with exact`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByAltText(eq("Logo"), any<Locator.GetByAltTextOptions>()) } returns child
        scope.getByAltText("Logo", exact = true)
        verify { locator.getByAltText("Logo", any<Locator.GetByAltTextOptions>()) }
    }

    @Test
    fun `getByTitle delegates`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByTitle(eq("Help"), any<Locator.GetByTitleOptions>()) } returns child
        val result = scope.getByTitle("Help")
        assertEquals(child, result.raw)
    }

    @Test
    fun `getByTitle with exact`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByTitle(eq("Help"), any<Locator.GetByTitleOptions>()) } returns child
        scope.getByTitle("Help", exact = true)
        verify { locator.getByTitle("Help", any<Locator.GetByTitleOptions>()) }
    }

    // State queries

    @Test
    fun `isVisible without options`() {
        every { locator.isVisible(any<Locator.IsVisibleOptions>()) } returns true
        assertTrue(scope.isVisible())
    }

    @Test
    fun `isVisible with options`() {
        every { locator.isVisible(any<Locator.IsVisibleOptions>()) } returns false
        assertFalse(scope.isVisible { })
    }

    @Test
    fun `isHidden without options`() {
        every { locator.isHidden(any<Locator.IsHiddenOptions>()) } returns true
        assertTrue(scope.isHidden())
    }

    @Test
    fun `isHidden with options`() {
        every { locator.isHidden(any<Locator.IsHiddenOptions>()) } returns false
        assertFalse(scope.isHidden { })
    }

    @Test
    fun `isEnabled without options`() {
        every { locator.isEnabled(any<Locator.IsEnabledOptions>()) } returns true
        assertTrue(scope.isEnabled())
    }

    @Test
    fun `isEnabled with options`() {
        every { locator.isEnabled(any<Locator.IsEnabledOptions>()) } returns false
        assertFalse(scope.isEnabled { })
    }

    @Test
    fun `isDisabled without options`() {
        every { locator.isDisabled(any<Locator.IsDisabledOptions>()) } returns true
        assertTrue(scope.isDisabled())
    }

    @Test
    fun `isDisabled with options`() {
        every { locator.isDisabled(any<Locator.IsDisabledOptions>()) } returns false
        assertFalse(scope.isDisabled { })
    }

    @Test
    fun `isChecked without options`() {
        every { locator.isChecked(any<Locator.IsCheckedOptions>()) } returns true
        assertTrue(scope.isChecked())
    }

    @Test
    fun `isChecked with options`() {
        every { locator.isChecked(any<Locator.IsCheckedOptions>()) } returns false
        assertFalse(scope.isChecked { })
    }

    @Test
    fun `isEditable without options`() {
        every { locator.isEditable(any<Locator.IsEditableOptions>()) } returns true
        assertTrue(scope.isEditable())
    }

    @Test
    fun `isEditable with options`() {
        every { locator.isEditable(any<Locator.IsEditableOptions>()) } returns false
        assertFalse(scope.isEditable { })
    }

    @Test
    fun `count delegates`() {
        every { locator.count() } returns 5
        assertEquals(5, scope.count())
    }

    // Content

    @Test
    fun `innerText without options`() {
        every { locator.innerText(any<Locator.InnerTextOptions>()) } returns "hello"
        assertEquals("hello", scope.innerText())
    }

    @Test
    fun `innerText with options`() {
        every { locator.innerText(any<Locator.InnerTextOptions>()) } returns "hello"
        assertEquals("hello", scope.innerText { })
    }

    @Test
    fun `innerHTML without options`() {
        every { locator.innerHTML(any<Locator.InnerHTMLOptions>()) } returns "<b>hello</b>"
        assertEquals("<b>hello</b>", scope.innerHTML())
    }

    @Test
    fun `innerHTML with options`() {
        every { locator.innerHTML(any<Locator.InnerHTMLOptions>()) } returns "<b>hello</b>"
        assertEquals("<b>hello</b>", scope.innerHTML { })
    }

    @Test
    fun `inputValue without options`() {
        every { locator.inputValue(any<Locator.InputValueOptions>()) } returns "test"
        assertEquals("test", scope.inputValue())
    }

    @Test
    fun `inputValue with options`() {
        every { locator.inputValue(any<Locator.InputValueOptions>()) } returns "test"
        assertEquals("test", scope.inputValue { })
    }

    @Test
    fun `getAttribute without options`() {
        every { locator.getAttribute(eq("href"), any<Locator.GetAttributeOptions>()) } returns "https://example.com"
        assertEquals("https://example.com", scope.getAttribute("href"))
    }

    @Test
    fun `getAttribute with options`() {
        every { locator.getAttribute(eq("href"), any<Locator.GetAttributeOptions>()) } returns null
        assertNull(scope.getAttribute("href") { })
    }

    @Test
    fun `textContent without options`() {
        every { locator.textContent(any<Locator.TextContentOptions>()) } returns "text"
        assertEquals("text", scope.textContent())
    }

    @Test
    fun `textContent with options returns null`() {
        every { locator.textContent(any<Locator.TextContentOptions>()) } returns null
        assertNull(scope.textContent { })
    }

    @Test
    fun `allInnerTexts delegates`() {
        every { locator.allInnerTexts() } returns listOf("a", "b")
        assertEquals(listOf("a", "b"), scope.allInnerTexts())
    }

    @Test
    fun `allTextContents delegates`() {
        every { locator.allTextContents() } returns listOf("x", "y")
        assertEquals(listOf("x", "y"), scope.allTextContents())
    }

    // Screenshot

    @Test
    fun `screenshot delegates to locator`() {
        val bytes = byteArrayOf(1, 2, 3)
        every { locator.screenshot(any<Locator.ScreenshotOptions>()) } returns bytes
        val result = scope.screenshot { setQuality(80) }
        assertEquals(bytes, result)
    }

    // Raw

    @Test
    fun `raw returns underlying locator`() {
        assertSame(locator, scope.raw)
    }
}
