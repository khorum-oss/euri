package org.khorum.oss.euri.dsl.config

import com.microsoft.playwright.Page
import com.microsoft.playwright.options.WaitUntilState
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue

@GeneratedDsl
data class NavigationConfig(
    val url: String,
    @DefaultValue("LOAD") val waitUntil: WaitUntilState = WaitUntilState.LOAD,
    @DefaultValue("30000.0") val timeout: Double = 30000.0,
    val referer: String? = null,
) : PlaywrightConfig<Page.NavigateOptions> {
    override fun toPlaywright(): Page.NavigateOptions = Page.NavigateOptions().also { options ->
        options.setTimeout(timeout)
        options.setWaitUntil(WaitUntilState.LOAD)
        options.setReferer(referer)
        return options
    }
}
