package org.khorum.oss.euri.dsl.runtime

import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.options.MouseButton
import org.khorum.oss.euri.dsl.enums.Role
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class LocatorScopeTest {

    private lateinit var locator: Locator
    private lateinit var scope: LocatorScope

    @BeforeEach
    fun setup() {
        locator = mockk(relaxed = true)
        scope = LocatorScope(mutableListOf())
    }

    @Nested
    inner class Actions {
        @Test
        fun `click processes on locator`() {
            scope.click { }
            scope.process(locator)
            verify { locator.click(any<Locator.ClickOptions>()) }
        }

        @Test
        fun `click with button`() {
            scope.click(button = MouseButton.RIGHT) { }
            scope.process(locator)
            verify { locator.click(match<Locator.ClickOptions> { true }) }
        }

        @Test
        fun `dblclick processes on locator`() {
            scope.dblclick { }
            scope.process(locator)
            verify { locator.dblclick(any<Locator.DblclickOptions>()) }
        }

        @Test
        fun `fill processes with value`() {
            scope.fill("text")
            scope.process(locator)
            verify { locator.fill("text", any<Locator.FillOptions>()) }
        }

        @Test
        fun `press processes with key`() {
            scope.press("Enter")
            scope.process(locator)
            verify { locator.press("Enter", any<Locator.PressOptions>()) }
        }

        @Test
        fun `pressSequentially processes with text`() {
            scope.pressSequentially("hello")
            scope.process(locator)
            verify { locator.pressSequentially("hello", any<Locator.PressSequentiallyOptions>()) }
        }

        @Test
        fun `check processes on locator`() {
            scope.check { }
            scope.process(locator)
            verify { locator.check(any<Locator.CheckOptions>()) }
        }

        @Test
        fun `uncheck processes on locator`() {
            scope.uncheck { }
            scope.process(locator)
            verify { locator.uncheck(any<Locator.UncheckOptions>()) }
        }

        @Test
        fun `hover processes on locator`() {
            scope.hover { }
            scope.process(locator)
            verify { locator.hover(any<Locator.HoverOptions>()) }
        }

        @Test
        fun `focus processes on locator`() {
            scope.focus { }
            scope.process(locator)
            verify { locator.focus(any<Locator.FocusOptions>()) }
        }

        @Test
        fun `blur processes on locator`() {
            scope.blur { }
            scope.process(locator)
            verify { locator.blur(any<Locator.BlurOptions>()) }
        }

        @Test
        fun `clear processes on locator`() {
            scope.clear { }
            scope.process(locator)
            verify { locator.clear(any<Locator.ClearOptions>()) }
        }

        @Test
        fun `selectOption with single value`() {
            every { locator.selectOption(any<Array<String>>(), any<Locator.SelectOptionOptions>()) } returns listOf("value1")
            val op = scope.selectOption("value1")
            scope.process(locator)
            assertEquals(listOf("value1"), op.selected)
        }

        @Test
        fun `selectOption with multiple values`() {
            every { locator.selectOption(any<Array<String>>(), any<Locator.SelectOptionOptions>()) } returns listOf("a", "b")
            val op = scope.selectOption(listOf("a", "b"))
            scope.process(locator)
            assertEquals(listOf("a", "b"), op.selected)
        }
    }

    @Nested
    inner class Chaining {
        @Test
        fun `locator narrows and processes children`() {
            val child = mockk<Locator>(relaxed = true)
            every { locator.locator(eq("span"), any<Locator.LocatorOptions>()) } returns child

            scope.locator("span") {
                click { }
            }
            scope.process(locator)

            verify { locator.locator("span", any<Locator.LocatorOptions>()) }
            verify { child.click(any<Locator.ClickOptions>()) }
        }

        @Test
        fun `nested locator chains`() {
            val mid = mockk<Locator>(relaxed = true)
            val inner = mockk<Locator>(relaxed = true)
            every { locator.locator(eq(".parent"), any<Locator.LocatorOptions>()) } returns mid
            every { mid.locator(eq(".child"), any<Locator.LocatorOptions>()) } returns inner

            scope.locator(".parent") {
                locator(".child") {
                    fill("hello")
                }
            }
            scope.process(locator)

            verify { locator.locator(".parent", any<Locator.LocatorOptions>()) }
            verify { mid.locator(".child", any<Locator.LocatorOptions>()) }
            verify { inner.fill("hello", any<Locator.FillOptions>()) }
        }

        @Test
        fun `filter narrows and processes children`() {
            val filtered = mockk<Locator>(relaxed = true)
            every { locator.filter(any<Locator.FilterOptions>()) } returns filtered

            scope.filter(hasText = "hello") {
                click { }
            }
            scope.process(locator)

            verify { locator.filter(any<Locator.FilterOptions>()) }
            verify { filtered.click(any<Locator.ClickOptions>()) }
        }

        @Test
        fun `first narrows and processes children`() {
            val first = mockk<Locator>(relaxed = true)
            every { locator.first() } returns first

            scope.first {
                hover { }
            }
            scope.process(locator)

            verify { locator.first() }
            verify { first.hover(any<Locator.HoverOptions>()) }
        }

        @Test
        fun `last narrows and processes children`() {
            val last = mockk<Locator>(relaxed = true)
            every { locator.last() } returns last

            scope.last {
                hover { }
            }
            scope.process(locator)

            verify { locator.last() }
            verify { last.hover(any<Locator.HoverOptions>()) }
        }

        @Test
        fun `nth narrows and processes children`() {
            val nth = mockk<Locator>(relaxed = true)
            every { locator.nth(2) } returns nth

            scope.nth(2) {
                click { }
            }
            scope.process(locator)

            verify { locator.nth(2) }
            verify { nth.click(any<Locator.ClickOptions>()) }
        }
    }

    @Nested
    inner class GetByChaining {
        @Test
        fun `getByRole narrows and processes children`() {
            val child = mockk<Locator>(relaxed = true)
            every { locator.getByRole(AriaRole.BUTTON, any<Locator.GetByRoleOptions>()) } returns child

            scope.getByRole(Role.Button, name = "Submit") {
                click { }
            }
            scope.process(locator)

            verify { locator.getByRole(AriaRole.BUTTON, any<Locator.GetByRoleOptions>()) }
            verify { child.click(any<Locator.ClickOptions>()) }
        }

        @Test
        fun `getByText narrows and processes children`() {
            val child = mockk<Locator>(relaxed = true)
            every { locator.getByText(eq("Hello"), any<Locator.GetByTextOptions>()) } returns child

            scope.getByText("Hello") {
                click { }
            }
            scope.process(locator)

            verify { locator.getByText("Hello", any<Locator.GetByTextOptions>()) }
            verify { child.click(any<Locator.ClickOptions>()) }
        }

        @Test
        fun `getByLabel narrows and processes children`() {
            val child = mockk<Locator>(relaxed = true)
            every { locator.getByLabel(eq("Username"), any<Locator.GetByLabelOptions>()) } returns child

            scope.getByLabel("Username") {
                fill("john")
            }
            scope.process(locator)

            verify { locator.getByLabel("Username", any<Locator.GetByLabelOptions>()) }
            verify { child.fill("john", any<Locator.FillOptions>()) }
        }

        @Test
        fun `getByPlaceholder narrows and processes children`() {
            val child = mockk<Locator>(relaxed = true)
            every { locator.getByPlaceholder(eq("Search"), any<Locator.GetByPlaceholderOptions>()) } returns child

            scope.getByPlaceholder("Search") {
                fill("query")
            }
            scope.process(locator)

            verify { locator.getByPlaceholder("Search", any<Locator.GetByPlaceholderOptions>()) }
            verify { child.fill("query", any<Locator.FillOptions>()) }
        }

        @Test
        fun `getByTestId narrows and processes children`() {
            val child = mockk<Locator>(relaxed = true)
            every { locator.getByTestId("test-id") } returns child

            scope.getByTestId("test-id") {
                click { }
            }
            scope.process(locator)

            verify { locator.getByTestId("test-id") }
            verify { child.click(any<Locator.ClickOptions>()) }
        }

        @Test
        fun `getByAltText narrows and processes children`() {
            val child = mockk<Locator>(relaxed = true)
            every { locator.getByAltText(eq("Logo"), any<Locator.GetByAltTextOptions>()) } returns child

            scope.getByAltText("Logo") {
                click { }
            }
            scope.process(locator)

            verify { locator.getByAltText("Logo", any<Locator.GetByAltTextOptions>()) }
            verify { child.click(any<Locator.ClickOptions>()) }
        }

        @Test
        fun `getByTitle narrows and processes children`() {
            val child = mockk<Locator>(relaxed = true)
            every { locator.getByTitle(eq("Help"), any<Locator.GetByTitleOptions>()) } returns child

            scope.getByTitle("Help") {
                click { }
            }
            scope.process(locator)

            verify { locator.getByTitle("Help", any<Locator.GetByTitleOptions>()) }
            verify { child.click(any<Locator.ClickOptions>()) }
        }
    }

    @Nested
    inner class StateQueries {
        @Test
        fun `isVisible stores result`() {
            every { locator.isVisible(any<Locator.IsVisibleOptions>()) } returns true
            val op = scope.isVisible()
            scope.process(locator)
            assertTrue(op.result)
        }

        @Test
        fun `isHidden stores result`() {
            every { locator.isHidden(any<Locator.IsHiddenOptions>()) } returns true
            val op = scope.isHidden()
            scope.process(locator)
            assertTrue(op.result)
        }

        @Test
        fun `isEnabled stores result`() {
            every { locator.isEnabled(any<Locator.IsEnabledOptions>()) } returns true
            val op = scope.isEnabled()
            scope.process(locator)
            assertTrue(op.result)
        }

        @Test
        fun `isDisabled stores result`() {
            every { locator.isDisabled(any<Locator.IsDisabledOptions>()) } returns false
            val op = scope.isDisabled()
            scope.process(locator)
            assertFalse(op.result)
        }

        @Test
        fun `isChecked stores result`() {
            every { locator.isChecked(any<Locator.IsCheckedOptions>()) } returns true
            val op = scope.isChecked()
            scope.process(locator)
            assertTrue(op.result)
        }

        @Test
        fun `isEditable stores result`() {
            every { locator.isEditable(any<Locator.IsEditableOptions>()) } returns true
            val op = scope.isEditable()
            scope.process(locator)
            assertTrue(op.result)
        }

        @Test
        fun `count stores result`() {
            every { locator.count() } returns 5
            val op = scope.count()
            scope.process(locator)
            assertEquals(5, op.result)
        }
    }

    @Nested
    inner class ContentQueries {
        @Test
        fun `innerText stores result`() {
            every { locator.innerText(any<Locator.InnerTextOptions>()) } returns "hello"
            val op = scope.innerText()
            scope.process(locator)
            assertEquals("hello", op.result)
        }

        @Test
        fun `innerHTML stores result`() {
            every { locator.innerHTML(any<Locator.InnerHTMLOptions>()) } returns "<b>hello</b>"
            val op = scope.innerHTML()
            scope.process(locator)
            assertEquals("<b>hello</b>", op.result)
        }

        @Test
        fun `inputValue stores result`() {
            every { locator.inputValue(any<Locator.InputValueOptions>()) } returns "test"
            val op = scope.inputValue()
            scope.process(locator)
            assertEquals("test", op.result)
        }

        @Test
        fun `getAttribute stores result`() {
            every { locator.getAttribute(eq("href"), any<Locator.GetAttributeOptions>()) } returns "https://example.com"
            val op = scope.getAttribute("href")
            scope.process(locator)
            assertEquals("https://example.com", op.result)
        }

        @Test
        fun `getAttribute stores null result`() {
            every { locator.getAttribute(eq("missing"), any<Locator.GetAttributeOptions>()) } returns null
            val op = scope.getAttribute("missing")
            scope.process(locator)
            assertNull(op.result)
        }

        @Test
        fun `textContent stores result`() {
            every { locator.textContent(any<Locator.TextContentOptions>()) } returns "text"
            val op = scope.textContent()
            scope.process(locator)
            assertEquals("text", op.result)
        }

        @Test
        fun `allInnerTexts stores result`() {
            every { locator.allInnerTexts() } returns listOf("a", "b")
            val op = scope.allInnerTexts()
            scope.process(locator)
            assertEquals(listOf("a", "b"), op.result)
        }

        @Test
        fun `allTextContents stores result`() {
            every { locator.allTextContents() } returns listOf("x", "y")
            val op = scope.allTextContents()
            scope.process(locator)
            assertEquals(listOf("x", "y"), op.result)
        }
    }

    @Nested
    inner class ResolvedLocator {
        @Test
        fun `process sets resolvedLocator`() {
            scope.process(locator)
            assertEquals(locator, scope.resolvedLocator)
        }
    }
}
