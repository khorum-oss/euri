package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.euri.dsl.runtime.LocatorScope
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl

@GeneratedDsl
class SelectorOperation(
    private val has: LocatorScope? = null,
    private val hasNot: LocatorScope? = null,
    val hasNotText: Any? = null,
    val hasText: Any? = null
) : PlaywrightConfig<Locator.LocatorOptions>, LocatorOperation {
    @TransientDsl
    internal var selector: String? = null

    @TransientDsl
    internal var childScope: LocatorScope? = null

    override fun process(locator: Locator) {
        val narrowed = locator.locator(selector, toPlaywright())
        childScope?.process(narrowed)
    }

    override fun toPlaywright(): Locator.LocatorOptions = Locator.LocatorOptions().also {
        it.has = has?.resolvedLocator
        it.hasNot = hasNot?.resolvedLocator
        it.hasText = hasText
        it.hasNotText = hasNotText
    }
}