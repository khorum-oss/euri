package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl

@GeneratedDsl
class IsHiddenOperation : PlaywrightConfig<Locator.IsHiddenOptions>, LocatorOperation {
    @TransientDsl
    var result: Boolean = false

    override fun process(locator: Locator) {
        result = locator.isHidden(toPlaywright())
    }

    override fun toPlaywright(): Locator.IsHiddenOptions = Locator.IsHiddenOptions()
}
