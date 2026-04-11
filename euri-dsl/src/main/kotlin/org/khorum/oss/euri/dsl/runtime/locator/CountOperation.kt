package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl

class CountOperation : LocatorOperation {
    @TransientDsl
    var result: Int = 0

    override fun process(locator: Locator) {
        result = locator.count()
    }
}
