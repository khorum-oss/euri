package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl

@GeneratedDsl
class IsVisibleOperation : PlaywrightConfig<Locator.IsVisibleOptions>, LocatorOperation {
    @TransientDsl
    var result: Boolean = false

    override fun process(locator: Locator) {
        result = locator.isVisible(toPlaywright())
    }

    override fun toPlaywright(): Locator.IsVisibleOptions = Locator.IsVisibleOptions()
}
