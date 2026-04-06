package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.euri.dsl.runtime.LocatorScope
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DO_NOT

@GeneratedDsl
class SelectorOperation(
    private val has: LocatorScope? = null,
    private val hasNot: LocatorScope? = null,
    val hasNotText: Any? = null,
    val hasText: Any? = null
) : PlaywrightConfig<Locator.LocatorOptions>, LocatorOperation {
    @TransientDsl
    internal var selector: String? = null

    override fun process(locator: Locator) {
        locator.locator(selector, toPlaywright())
    }

    override fun toPlaywright(): Locator.LocatorOptions = Locator.LocatorOptions().also {
        it.has = has?.locator
        it.hasNot = hasNot?.locator
        it.hasText = hasText
        it.hasNotText = hasNotText
    }
}