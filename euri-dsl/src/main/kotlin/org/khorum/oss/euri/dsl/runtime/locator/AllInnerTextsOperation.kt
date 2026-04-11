package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl

class AllInnerTextsOperation : LocatorOperation {
    @TransientDsl
    var result: List<String> = emptyList()

    override fun process(locator: Locator) {
        result = locator.allInnerTexts()
    }
}
