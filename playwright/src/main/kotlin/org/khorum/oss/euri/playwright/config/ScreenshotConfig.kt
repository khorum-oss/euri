package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultStateType
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl

@GeneratedDsl
data class ScreenshotConfig(
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val path: String = "",
    @DefaultValue("PNG") val type: String = "PNG",
    @DefaultValue("-1") val quality: Int = -1,
    @DefaultState(type = DefaultStateType.FALSE) val fullPage: Boolean = false,
    val clip: ClipConfig? = null,
    @DefaultState(type = DefaultStateType.FALSE) val omitBackground: Boolean = false,
    @DefaultState(type = DefaultStateType.EMPTY_STRING) val style: String = "",
    @ListDsl val maskSelectors: List<String> = emptyList(),
)
