package org.khorum.oss.euri.dsl.config

import com.microsoft.playwright.Page
import org.khorum.oss.euri.dsl.enums.WaitUntil
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultEnum
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue

@GeneratedDsl
data class NavigationConfig(
    val url: String,
    @DefaultEnum(
        value = "Load",
        packageName = "org.khorum.oss.euri.dsl.enums",
        className = "WaitUntil"
    )
    val waitUntil: WaitUntil = WaitUntil.Load,
    @DefaultValue("30000.0") val timeout: Double = 30000.0,
    val referer: String? = null,
) : PlaywrightConfig<Page.NavigateOptions> {
    override fun toPlaywright(): Page.NavigateOptions = Page.NavigateOptions().also { options ->
        options.setTimeout(timeout)
        options.setWaitUntil(waitUntil.toPlaywright())
        options.setReferer(referer)
        return options
    }
}
