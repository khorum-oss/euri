package org.khorum.oss.euri.dsl.config

import com.microsoft.playwright.Page
import org.khorum.oss.euri.dsl.common.toPath
import org.khorum.oss.euri.dsl.enums.ScreenshotType
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultEnum
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyList
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DO_NOT
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.NOT

@GeneratedDsl
data class ScreenshotConfig(
    @DefaultEnum(
        value = "Png",
        packageName = "org.khorum.oss.euri.dsl.enums",
        className = "ScreenshotType"
    )
    val type: ScreenshotType = ScreenshotType.Png,
    @DefaultValue("-1") val quality: Int = -1,
    val path: String? = null,
    val style: String? = null,

    @DefaultFalse(negationTemplate = NOT) val fullPage: Boolean = false,
    @DefaultFalse(negationTemplate = DO_NOT) val omitBackground: Boolean = false,

    @DefaultEmptyList @ListDsl val maskSelectors: List<String> = emptyList(),

    val clip: ClipConfig? = null,
) : PlaywrightConfig<Page.ScreenshotOptions> {
    override fun toPlaywright(): Page.ScreenshotOptions = Page.ScreenshotOptions().also { options ->
        options.setType(type.toPlaywright())
        options.setPath(path?.toPath())
        options.setFullPage(fullPage)
        options.setOmitBackground(omitBackground)
        clip?.let { options.setClip(it.x, it.y, it.width, it.height) }
        if (quality >= 0) options.setQuality(quality)
        options.setStyle(style)
    }
}
