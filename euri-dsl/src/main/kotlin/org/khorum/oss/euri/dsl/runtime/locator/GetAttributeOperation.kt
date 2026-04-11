package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue

@GeneratedDsl
class GetAttributeOperation(
    @DefaultValue("30000.0")
    val timeout: Double = 30000.0
) : PlaywrightConfig<Locator.GetAttributeOptions>, LocatorOperation {
    @TransientDsl
    internal var name: String? = null

    @TransientDsl
    var result: String? = null

    override fun process(locator: Locator) {
        result = locator.getAttribute(name, toPlaywright())
    }

    override fun toPlaywright(): Locator.GetAttributeOptions = Locator.GetAttributeOptions().also {
        it.timeout = timeout
    }
}
