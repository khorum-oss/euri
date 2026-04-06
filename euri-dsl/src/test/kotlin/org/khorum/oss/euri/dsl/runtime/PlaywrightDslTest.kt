package org.khorum.oss.euri.dsl.runtime

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class PlaywrightDslTest {

    @Test
    fun `PlaywrightDsl can be instantiated`() {
        PlaywrightRuntime()
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
    fun `playwright function creates and applies DSL`() {
        var dslBlockCalled = false
        playwright {
            dslBlockCalled = true
        }
        assertTrue(dslBlockCalled)
    }
}
