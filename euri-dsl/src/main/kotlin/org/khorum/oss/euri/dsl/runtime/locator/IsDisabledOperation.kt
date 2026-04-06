package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue

@GeneratedDsl
class IsDisabledOperation(
    @DefaultValue("30000.0")
    val timeout: Double = 30000.0
) : PlaywrightConfig<Locator.IsDisabledOptions>, LocatorOperation {
    @TransientDsl
    var result: Boolean = false

    override fun process(locator: Locator) {
        result = locator.isDisabled(toPlaywright())
    }

    override fun toPlaywright(): Locator.IsDisabledOptions = Locator.IsDisabledOptions().also {
        it.timeout = timeout
    }
}
