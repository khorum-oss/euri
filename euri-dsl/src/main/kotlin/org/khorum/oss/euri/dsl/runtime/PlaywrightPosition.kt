package org.khorum.oss.euri.dsl.runtime

import com.microsoft.playwright.options.Position
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class PlaywrightPosition(
    val x: Double,
    val y: Double
) : PlaywrightConfig<Position> {
    override fun toPlaywright(): Position = Position(x, y)
}