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
class FilterOperation(
    private val has: LocatorScope? = null,
    private val hasNot: LocatorScope? = null,
    val hasNotText: Any? = null,
    val hasText: Any? = null
) : PlaywrightConfig<Locator.FilterOptions>, LocatorOperation {
    override fun process(locator: Locator) {
        locator.filter(toPlaywright())
    }

    override fun toPlaywright(): Locator.FilterOptions = Locator.FilterOptions().also {
        it.has = has?.locator
        it.hasNot = hasNot?.locator
        it.hasText = hasText
        it.hasNotText = hasNotText
    }
}