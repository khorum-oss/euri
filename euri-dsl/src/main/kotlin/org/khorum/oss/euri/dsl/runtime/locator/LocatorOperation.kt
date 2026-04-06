package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator

interface LocatorOperation {
    fun process(locator: Locator)
}
