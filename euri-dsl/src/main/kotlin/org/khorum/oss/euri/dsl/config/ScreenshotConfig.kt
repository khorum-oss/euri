package org.khorum.oss.euri.dsl.config

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyList
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyString
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse

@GeneratedDsl
data class ScreenshotConfig(
    @DefaultEmptyString val path: String = "",
    @DefaultValue("PNG") val type: String = "PNG",
    @DefaultValue("-1") val quality: Int = -1,
    @DefaultFalse val fullPage: Boolean = false,
    val clip: ClipConfig? = null,
    @DefaultFalse val omitBackground: Boolean = false,
    @DefaultEmptyString val style: String = "",
    @DefaultEmptyList @ListDsl val maskSelectors: List<String> = emptyList(),
)
