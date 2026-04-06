package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.euri.dsl.runtime.LocatorScope
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl

@GeneratedDsl
class FilterOperation(
    private val has: LocatorScope? = null,
    private val hasNot: LocatorScope? = null,
    val hasNotText: Any? = null,
    val hasText: Any? = null
) : PlaywrightConfig<Locator.FilterOptions>, LocatorOperation {
    @TransientDsl
    internal var childScope: LocatorScope? = null

    override fun process(locator: Locator) {
        val narrowed = locator.filter(toPlaywright())
        childScope?.process(narrowed)
    }

    override fun toPlaywright(): Locator.FilterOptions = Locator.FilterOptions().also {
        it.has = has?.resolvedLocator
        it.hasNot = hasNot?.resolvedLocator
        it.hasText = hasText
        it.hasNotText = hasNotText
    }
}