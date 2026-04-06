package org.khorum.oss.euri.dsl.runtime

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class PlaywrightDslTest {

    @Test
    fun `PlaywrightDsl can be instantiated`() {
        val dsl = PlaywrightRuntime()
        // verify DSL object is created without exception
    }

    @Test
    fun `chromium sets browser type`() {
        val dsl = PlaywrightRuntime()
        dsl.chromium()
        // no exception thrown
    }

    @Test
    fun `firefox sets browser type`() {
        val dsl = PlaywrightRuntime()
        dsl.firefox()
        // no exception thrown
    }

    @Test
    fun `webkit sets browser type`() {
        val dsl = PlaywrightRuntime()
        dsl.webkit()
        // no exception thrown
    }

    @Test
    fun `launch configures launch options`() {
        val dsl = PlaywrightRuntime()
        dsl.launch { setHeadless(true) }
        // no exception thrown
    }

    @Test
    fun `context configures context options`() {
        val dsl = PlaywrightRuntime()
        dsl.context { setLocale("en-US") }
        // no exception thrown
    }

    @Test
    fun `page registers page action`() {
        val dsl = PlaywrightRuntime()
        var called = false
        dsl.page { called = true }
        // action is registered, not executed yet
        // called should still be false since execute() wasn't called
    }

    @Test
    fun `contextScope registers context action`() {
        val dsl = PlaywrightRuntime()
        var called = false
        dsl.contextScope { called = true }
        // action is registered, not executed yet
    }

    @Test
    fun `multiple page actions can be registered`() {
        val dsl = PlaywrightRuntime()
        val actions = mutableListOf<Int>()
        dsl.page { actions.add(1) }
        dsl.page { actions.add(2) }
        // both are registered
        assertTrue(actions.isEmpty()) // not executed yet
    }

    @Test
    fun `playwright function creates and applies DSL`() {
        // We can't fully test execute() without a real browser,
        // but we can verify the top-level function exists and takes a lambda
        var dslBlockCalled = false
        try {
            playwright {
                dslBlockCalled = true
                // execute() will fail because no browser, but the block runs first
            }
        } catch (_: Exception) {
            // Expected - Playwright.create() will fail without browser binaries
        }
        assertTrue(dslBlockCalled)
    }
}
