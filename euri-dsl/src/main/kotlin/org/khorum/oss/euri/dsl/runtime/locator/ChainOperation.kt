package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.runtime.LocatorScope

class ChainOperation(
    private val narrow: (Locator) -> Locator,
    private val childScope: LocatorScope
) : LocatorOperation {
    override fun process(locator: Locator) {
        val narrowed = narrow(locator)
        childScope.process(narrowed)
    }
}
