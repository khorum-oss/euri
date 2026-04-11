package org.khorum.oss.euri.dsl.runtime

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PlaywrightPositionTest {

    @Test
    fun `toPlaywright creates Position with correct x and y`() {
        val pos = PlaywrightPosition(10.5, 20.3)
        val result = pos.toPlaywright()
        assertEquals(10.5, result.x)
        assertEquals(20.3, result.y)
    }

    @Test
    fun `data class equality works`() {
        val a = PlaywrightPosition(1.0, 2.0)
        val b = PlaywrightPosition(1.0, 2.0)
        assertEquals(a, b)
    }
}
