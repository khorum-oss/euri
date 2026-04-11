package org.khorum.oss.euri.dsl.config

import com.microsoft.playwright.options.ScreenSize
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class ScreenSizeConfig(
    val width: Int,
    val height: Int,
) : PlaywrightConfig<ScreenSize> {
    override fun toPlaywright(): ScreenSize = ScreenSize(width, height)
}
