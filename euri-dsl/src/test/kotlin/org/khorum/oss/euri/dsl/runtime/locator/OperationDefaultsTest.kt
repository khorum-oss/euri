package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.AriaRole
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.khorum.oss.euri.dsl.enums.Role
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

/**
 * Tests that exercise default constructor values and null childScope paths.
 * DslBuilder always passes all values explicitly, so default value expressions
 * are only exercised by calling the constructor without arguments.
 */
@Suppress("TooManyFunctions")
class OperationDefaultsTest {

    private val locator: Locator = mockk(relaxed = true)

    // ── Config operations with default timeout ──────────────────────────

    @Test
    fun `BlurOperation defaults`() {
        val op = BlurOperation()
        assertEquals(30000.0, op.timeout)
        op.process(locator)
        verify { locator.blur(any<Locator.BlurOptions>()) }
    }

    @Test
    fun `FocusOperation defaults`() {
        val op = FocusOperation()
        assertEquals(30000.0, op.timeout)
        op.process(locator)
        verify { locator.focus(any<Locator.FocusOptions>()) }
    }

    @Test
    fun `ClearOperation defaults`() {
        val op = ClearOperation()
        assertFalse(op.force)
        assertEquals(30000.0, op.timeout)
        op.process(locator)
        verify { locator.clear(any<Locator.ClearOptions>()) }
    }

    @Test
    fun `ClickOperation defaults`() {
        val op = ClickOperation()
        assertEquals(1, op.clickCount)
        assertEquals(0.0, op.delay)
        assertFalse(op.force)
        assertEquals(30000.0, op.timeout)
        assertFalse(op.trial)
        op.process(locator)
        verify { locator.click(any<Locator.ClickOptions>()) }
    }

    @Test
    fun `DoubleClickOperation defaults`() {
        val op = DoubleClickOperation()
        assertEquals(0.0, op.delay)
        assertFalse(op.force)
        assertEquals(30000.0, op.timeout)
        assertFalse(op.trial)
        op.process(locator)
        verify { locator.dblclick(any<Locator.DblclickOptions>()) }
    }

    @Test
    fun `HoverOperation defaults`() {
        val op = HoverOperation()
        assertFalse(op.force)
        assertEquals(30000.0, op.timeout)
        assertFalse(op.trial)
        op.process(locator)
        verify { locator.hover(any<Locator.HoverOptions>()) }
    }

    @Test
    fun `FillOperation defaults`() {
        val op = FillOperation()
        assertFalse(op.force)
        assertEquals(30000.0, op.timeout)
        op.value = "test"
        op.process(locator)
        verify { locator.fill("test", any<Locator.FillOptions>()) }
    }

    @Test
    fun `SelectOptionOperation defaults`() {
        val op = SelectOptionOperation()
        assertFalse(op.force)
        assertEquals(30000.0, op.timeout)
        every { locator.selectOption(any<Array<String>>(), any<Locator.SelectOptionOptions>()) } returns listOf("v")
        op.values = listOf("v")
        op.process(locator)
        assertEquals(listOf("v"), op.selected)
    }

    // ── State query operations with default timeout ─────────────────────

    @Test
    fun `IsEnabledOperation defaults`() {
        val op = IsEnabledOperation()
        assertEquals(30000.0, op.timeout)
    }

    @Test
    fun `IsDisabledOperation defaults`() {
        val op = IsDisabledOperation()
        assertEquals(30000.0, op.timeout)
    }

    @Test
    fun `IsCheckedOperation defaults`() {
        val op = IsCheckedOperation()
        assertEquals(30000.0, op.timeout)
    }

    @Test
    fun `IsEditableOperation defaults`() {
        val op = IsEditableOperation()
        assertEquals(30000.0, op.timeout)
    }

    // ── Content query operations with default timeout ───────────────────

    @Test
    fun `InnerHtmlOperation defaults`() {
        val op = InnerHtmlOperation()
        assertEquals(30000.0, op.timeout)
    }

    @Test
    fun `InnerTextOperation defaults`() {
        val op = InnerTextOperation()
        assertEquals(30000.0, op.timeout)
    }

    @Test
    fun `InputValueOperation defaults`() {
        val op = InputValueOperation()
        assertEquals(30000.0, op.timeout)
    }

    @Test
    fun `GetAttributeOperation defaults`() {
        val op = GetAttributeOperation()
        assertEquals(30000.0, op.timeout)
    }

    @Test
    fun `TextContentOperation defaults`() {
        val op = TextContentOperation()
        assertEquals(30000.0, op.timeout)
    }

    // ── GetBy operations with default exact and null childScope ─────────

    @Test
    fun `GetByTextOperation defaults and null childScope`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByText(any<String>(), any<Locator.GetByTextOptions>()) } returns child
        val op = GetByTextOperation()
        assertFalse(op.exact)
        op.text = "hello"
        // childScope is null by default
        op.process(locator)
        verify { locator.getByText("hello", any<Locator.GetByTextOptions>()) }
    }

    @Test
    fun `GetByLabelOperation defaults and null childScope`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByLabel(any<String>(), any<Locator.GetByLabelOptions>()) } returns child
        val op = GetByLabelOperation()
        assertFalse(op.exact)
        op.text = "Username"
        op.process(locator)
        verify { locator.getByLabel("Username", any<Locator.GetByLabelOptions>()) }
    }

    @Test
    fun `GetByPlaceholderOperation defaults and null childScope`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByPlaceholder(any<String>(), any<Locator.GetByPlaceholderOptions>()) } returns child
        val op = GetByPlaceholderOperation()
        assertFalse(op.exact)
        op.text = "Search"
        op.process(locator)
        verify { locator.getByPlaceholder("Search", any<Locator.GetByPlaceholderOptions>()) }
    }

    @Test
    fun `GetByAltTextOperation defaults and null childScope`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByAltText(any<String>(), any<Locator.GetByAltTextOptions>()) } returns child
        val op = GetByAltTextOperation()
        assertFalse(op.exact)
        op.text = "Logo"
        op.process(locator)
        verify { locator.getByAltText("Logo", any<Locator.GetByAltTextOptions>()) }
    }

    @Test
    fun `GetByTitleOperation defaults and null childScope`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByTitle(any<String>(), any<Locator.GetByTitleOptions>()) } returns child
        val op = GetByTitleOperation()
        assertFalse(op.exact)
        op.text = "Help"
        op.process(locator)
        verify { locator.getByTitle("Help", any<Locator.GetByTitleOptions>()) }
    }

    @Test
    fun `GetByRoleOperation defaults and null childScope`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByRole(any<AriaRole>(), any<Locator.GetByRoleOptions>()) } returns child
        val op = GetByRoleOperation()
        op.role = Role.Button
        // childScope is null, all option fields are null
        op.process(locator)
        verify { locator.getByRole(AriaRole.BUTTON, any<Locator.GetByRoleOptions>()) }
    }

    @Test
    fun `GetByRoleOperation with all options set`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByRole(any<AriaRole>(), any<Locator.GetByRoleOptions>()) } returns child
        val op = GetByRoleOperation(
            checked = true,
            disabled = false,
            exact = true,
            expanded = true,
            includeHidden = false,
            level = 2,
            name = "Submit",
            pressed = true,
            selected = false
        )
        op.role = Role.Button
        op.process(locator)
        verify { locator.getByRole(AriaRole.BUTTON, any<Locator.GetByRoleOptions>()) }
    }

    // ── SelectorOperation and FilterOperation with null childScope ──────

    @Test
    fun `SelectorOperation defaults and null childScope`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.locator(any<String>(), any<Locator.LocatorOptions>()) } returns child
        val op = SelectorOperation()
        op.selector = "div"
        // childScope is null
        op.process(locator)
        verify { locator.locator("div", any<Locator.LocatorOptions>()) }
    }

    @Test
    fun `FilterOperation defaults and null childScope`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.filter(any<Locator.FilterOptions>()) } returns child
        val op = FilterOperation()
        // childScope is null
        op.process(locator)
        verify { locator.filter(any<Locator.FilterOptions>()) }
    }

    @Test
    fun `FilterOperation with hasText and hasNotText`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.filter(any<Locator.FilterOptions>()) } returns child
        val op = FilterOperation(hasText = "hello", hasNotText = "world")
        op.process(locator)
        val options = op.toPlaywright()
        assertNotNull(options)
    }

    @Test
    fun `FilterOperation with has and hasNot LocatorScopes`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.filter(any<Locator.FilterOptions>()) } returns child
        val hasScope = org.khorum.oss.euri.dsl.runtime.LocatorScope(mutableListOf())
        hasScope.process(mockk(relaxed = true))
        val hasNotScope = org.khorum.oss.euri.dsl.runtime.LocatorScope(mutableListOf())
        hasNotScope.process(mockk(relaxed = true))
        val op = FilterOperation(has = hasScope, hasNot = hasNotScope, hasText = "hi")
        op.process(locator)
        val options = op.toPlaywright()
        assertNotNull(options.has)
        assertNotNull(options.hasNot)
    }

    @Test
    fun `SelectorOperation with hasText and hasNotText`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.locator(any<String>(), any<Locator.LocatorOptions>()) } returns child
        val op = SelectorOperation(hasText = "hello", hasNotText = "world")
        op.selector = "div"
        op.process(locator)
        val options = op.toPlaywright()
        assertNotNull(options)
    }

    @Test
    fun `SelectorOperation with has and hasNot LocatorScopes`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.locator(any<String>(), any<Locator.LocatorOptions>()) } returns child
        val hasScope = org.khorum.oss.euri.dsl.runtime.LocatorScope(mutableListOf())
        hasScope.process(mockk(relaxed = true))
        val hasNotScope = org.khorum.oss.euri.dsl.runtime.LocatorScope(mutableListOf())
        hasNotScope.process(mockk(relaxed = true))
        val op = SelectorOperation(has = hasScope, hasNot = hasNotScope)
        op.selector = "div"
        op.process(locator)
        val options = op.toPlaywright()
        assertNotNull(options.has)
        assertNotNull(options.hasNot)
    }

    // ── Null-safe branches for transient fields ─────────────────────────

    @Test
    fun `GetByRoleOperation with null role`() {
        val child = mockk<Locator>(relaxed = true)
        every { locator.getByRole(any(), any<Locator.GetByRoleOptions>()) } returns child
        val op = GetByRoleOperation()
        // role is null by default
        op.process(locator)
        verify { locator.getByRole(null, any<Locator.GetByRoleOptions>()) }
    }

    @Test
    fun `SelectOptionOperation with null values`() {
        every { locator.selectOption(any<Array<String>>(), any<Locator.SelectOptionOptions>()) } returns emptyList()
        val op = SelectOptionOperation()
        // values is null by default
        op.process(locator)
    }
}
