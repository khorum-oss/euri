package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.KeyboardModifier
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.euri.dsl.runtime.PlaywrightPosition
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DO_NOT

@GeneratedDsl
class HoverOperation(
    @DefaultFalse(negationTemplate = DO_NOT)
    val force: Boolean = false,
    @ListDsl
    private val modifiers: List<KeyboardModifier>? = null,
    private val position: PlaywrightPosition? = null,
    @DefaultValue("30000.0")
    val timeout: Double = 30000.0,
    @DefaultFalse
    val trial: Boolean = false
) : PlaywrightConfig<Locator.HoverOptions>, LocatorOperation {

    override fun toPlaywright(): Locator.HoverOptions = Locator.HoverOptions().also { options ->
        options.setForce(force)
        modifiers?.let { options.setModifiers(it) }
        position?.let { options.setPosition(it.toPlaywright()) }
        options.setTimeout(timeout)
        options.setTrial(trial)
    }

    override fun process(locator: Locator) {
        locator.hover(toPlaywright())
    }
}