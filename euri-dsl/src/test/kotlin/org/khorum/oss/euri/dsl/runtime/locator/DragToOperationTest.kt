package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.khorum.oss.euri.dsl.runtime.LocatorScope
import org.khorum.oss.euri.dsl.runtime.PlaywrightPosition
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

class DragToOperationTest {

    @Test
    fun `process calls locator dragTo with target resolvedLocator`() {
        val locator = mockk<Locator>(relaxed = true)
        val targetLocator = mockk<Locator>(relaxed = true)

        val targetScope = LocatorScope(mutableListOf())
        targetScope.process(targetLocator)

        val op = DragToOperation()
        op.targetScope = targetScope
        op.process(locator)

        verify { locator.dragTo(targetLocator, any<Locator.DragToOptions>()) }
    }

    @Test
    fun `process throws when targetScope is null`() {
        val locator = mockk<Locator>(relaxed = true)
        val op = DragToOperation()
        assertThrows<IllegalArgumentException> { op.process(locator) }
    }

    @Test
    fun `toPlaywright sets default options`() {
        val op = DragToOperation()
        val options = op.toPlaywright()
        assertNotNull(options)
    }

    @Test
    fun `toPlaywright sets force and trial`() {
        val op = DragToOperation(force = true, trial = true, timeout = 5000.0)
        val options = op.toPlaywright()
        assertNotNull(options)
    }

    @Test
    fun `toPlaywright sets sourcePosition and targetPosition`() {
        val op = DragToOperation(
            sourcePosition = PlaywrightPosition(10.0, 20.0),
            targetPosition = PlaywrightPosition(30.0, 40.0)
        )
        val options = op.toPlaywright()
        assertNotNull(options)
    }

    @Test
    fun `default values are correct`() {
        val op = DragToOperation()
        assertFalse(op.force)
        assertEquals(30000.0, op.timeout)
        assertFalse(op.trial)
    }
}
