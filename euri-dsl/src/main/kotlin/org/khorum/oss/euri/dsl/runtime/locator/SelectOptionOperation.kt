package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DO_NOT

@GeneratedDsl
class SelectOptionOperation(
    @DefaultFalse(negationTemplate = DO_NOT)
    val force: Boolean = false,
    @DefaultValue("30000.0")
    val timeout: Double = 30000.0
) : PlaywrightConfig<Locator.SelectOptionOptions>, LocatorOperation {
    @TransientDsl
    internal var values: List<String>? = null
    @TransientDsl
    var selected: List<String> = emptyList()

    override fun process(locator: Locator) {
        selected = locator.selectOption(values?.toTypedArray(), toPlaywright())
    }

    override fun toPlaywright(): Locator.SelectOptionOptions = Locator.SelectOptionOptions().also {
        it.force = force
        it.timeout = timeout
    }
}