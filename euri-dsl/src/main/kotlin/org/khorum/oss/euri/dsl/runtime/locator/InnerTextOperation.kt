package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue

@GeneratedDsl
class InnerTextOperation(
    @DefaultValue("30000.0")
    val timeout: Double = 30000.0
) : PlaywrightConfig<Locator.InnerTextOptions>, LocatorOperation {
    @TransientDsl
    var result: String = ""

    override fun process(locator: Locator) {
        result = locator.innerText(toPlaywright())
    }

    override fun toPlaywright(): Locator.InnerTextOptions = Locator.InnerTextOptions().also {
        it.timeout = timeout
    }
}
