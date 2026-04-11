package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.KeyboardModifier
import com.microsoft.playwright.options.MouseButton
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.khorum.oss.euri.dsl.runtime.PlaywrightPosition
import kotlin.test.assertNotNull

class OperationOptionsTest {

    // ── ClickOperation ──────────────────────────────────────────────────

    @Test
    fun `ClickOperation toPlaywright with modifiers and position`() {
        val op = ClickOperation(
            modifiers = listOf(KeyboardModifier.ALT, KeyboardModifier.SHIFT),
            position = PlaywrightPosition(10.0, 20.0)
        )
        val options = op.toPlaywright()
        assertNotNull(options)
    }

    @Test
    fun `ClickOperation process with modifiers and position`() {
        val locator = mockk<Locator>(relaxed = true)
        val op = ClickOperation(
            modifiers = listOf(KeyboardModifier.ALT),
            position = PlaywrightPosition(5.0, 15.0)
        )
        op.process(locator)
        verify { locator.click(any<Locator.ClickOptions>()) }
    }

    // ── DoubleClickOperation ────────────────────────────────────────────

    @Test
    fun `DoubleClickOperation toPlaywright with modifiers and position`() {
        val op = DoubleClickOperation(
            modifiers = listOf(KeyboardModifier.CONTROL),
            position = PlaywrightPosition(30.0, 40.0)
        )
        val options = op.toPlaywright()
        assertNotNull(options)
    }

    @Test
    fun `DoubleClickOperation process with modifiers and position`() {
        val locator = mockk<Locator>(relaxed = true)
        val op = DoubleClickOperation(
            modifiers = listOf(KeyboardModifier.META),
            position = PlaywrightPosition(50.0, 60.0)
        )
        op.process(locator)
        verify { locator.dblclick(any<Locator.DblclickOptions>()) }
    }

    // ── HoverOperation ──────────────────────────────────────────────────

    @Test
    fun `HoverOperation toPlaywright with modifiers and position`() {
        val op = HoverOperation(
            modifiers = listOf(KeyboardModifier.SHIFT),
            position = PlaywrightPosition(70.0, 80.0)
        )
        val options = op.toPlaywright()
        assertNotNull(options)
    }

    @Test
    fun `HoverOperation process with modifiers and position`() {
        val locator = mockk<Locator>(relaxed = true)
        val op = HoverOperation(
            modifiers = listOf(KeyboardModifier.ALT),
            position = PlaywrightPosition(1.0, 2.0)
        )
        op.process(locator)
        verify { locator.hover(any<Locator.HoverOptions>()) }
    }

    // ── CheckableOperation ──────────────────────────────────────────────

    @Test
    fun `CheckableOperation toCheckOption with position`() {
        val op = CheckableOperation(
            position = PlaywrightPosition(100.0, 200.0)
        )
        val options = op.toCheckOption()
        assertNotNull(options)
    }

    @Test
    fun `CheckableOperation toUncheckOption with position`() {
        val op = CheckableOperation(
            position = PlaywrightPosition(100.0, 200.0)
        )
        val options = op.toUncheckOption()
        assertNotNull(options)
    }

    @Test
    fun `CheckableOperation process with null type does nothing`() {
        val locator = mockk<Locator>(relaxed = true)
        val op = CheckableOperation()
        // type is null by default - should hit the else -> Unit branch
        op.process(locator)
        verify(exactly = 0) { locator.check(any()) }
        verify(exactly = 0) { locator.uncheck(any()) }
    }

    @Test
    fun `CheckableOperation process with CHECKED type and position`() {
        val locator = mockk<Locator>(relaxed = true)
        val op = CheckableOperation(position = PlaywrightPosition(10.0, 20.0))
        op.type = CheckableOperation.Type.CHECKED
        op.process(locator)
        verify { locator.check(any<Locator.CheckOptions>()) }
    }

    @Test
    fun `CheckableOperation process with UNCHECKED type and position`() {
        val locator = mockk<Locator>(relaxed = true)
        val op = CheckableOperation(position = PlaywrightPosition(10.0, 20.0))
        op.type = CheckableOperation.Type.UNCHECKED
        op.process(locator)
        verify { locator.uncheck(any<Locator.UncheckOptions>()) }
    }

    // ── KeyOperation ────────────────────────────────────────────────────

    @Test
    fun `KeyOperation process with null type does nothing`() {
        val locator = mockk<Locator>(relaxed = true)
        val op = KeyOperation()
        // type is null by default - should hit the else -> Unit branch
        op.process(locator)
        verify(exactly = 0) { locator.press(any(), any()) }
        verify(exactly = 0) { locator.pressSequentially(any(), any()) }
    }

    @Test
    fun `KeyOperation process with PRESS type`() {
        val locator = mockk<Locator>(relaxed = true)
        val op = KeyOperation(delay = 100.0, timeout = 5000.0)
        op.text = "Enter"
        op.type = KeyOperation.Type.PRESS
        op.process(locator)
        verify { locator.press("Enter", any<Locator.PressOptions>()) }
    }

    @Test
    fun `KeyOperation process with PRESS_SEQUENTIALLY type`() {
        val locator = mockk<Locator>(relaxed = true)
        val op = KeyOperation(delay = 50.0, timeout = 10000.0)
        op.text = "hello"
        op.type = KeyOperation.Type.PRESS_SEQUENTIALLY
        op.process(locator)
        verify { locator.pressSequentially("hello", any<Locator.PressSequentiallyOptions>()) }
    }

    // ── FillOperation ───────────────────────────────────────────────────

    @Test
    fun `FillOperation toPlaywright with force`() {
        val op = FillOperation(force = true, timeout = 5000.0)
        val options = op.toPlaywright()
        assertNotNull(options)
    }

    @Test
    fun `FillOperation process`() {
        val locator = mockk<Locator>(relaxed = true)
        val op = FillOperation(force = true)
        op.value = "test"
        op.process(locator)
        verify { locator.fill("test", any<Locator.FillOptions>()) }
    }

    // ── ClearOperation ──────────────────────────────────────────────────

    @Test
    fun `ClearOperation toPlaywright with force`() {
        val op = ClearOperation(force = true, timeout = 5000.0)
        val options = op.toPlaywright()
        assertNotNull(options)
    }

    // ── SelectOptionOperation ───────────────────────────────────────────

    @Test
    fun `SelectOptionOperation toPlaywright with force`() {
        val op = SelectOptionOperation(force = true, timeout = 5000.0)
        val options = op.toPlaywright()
        assertNotNull(options)
    }

    // ── FocusOperation ──────────────────────────────────────────────────

    @Test
    fun `FocusOperation toPlaywright with custom timeout`() {
        val op = FocusOperation(timeout = 5000.0)
        val options = op.toPlaywright()
        assertNotNull(options)
    }

    // ── BlurOperation ───────────────────────────────────────────────────

    @Test
    fun `BlurOperation toPlaywright with custom timeout`() {
        val op = BlurOperation(timeout = 5000.0)
        val options = op.toPlaywright()
        assertNotNull(options)
    }
}
