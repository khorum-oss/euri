package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.KeyboardModifier
import com.microsoft.playwright.options.MouseButton
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.euri.dsl.runtime.PlaywrightPosition
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DO_NOT

@GeneratedDsl
class DoubleClickOperation(
    var button: MouseButton = MouseButton.LEFT,
    @DefaultValue("0.0")
    val delay: Double = 0.0,
    @DefaultFalse(negationTemplate = DO_NOT)
    val force: Boolean = false,
    @ListDsl
    private val modifiers: List<KeyboardModifier>? = null,
    private val position: PlaywrightPosition? = null,
    @DefaultValue("30000.0")
    val timeout: Double = 30000.0,
    @DefaultFalse
    val trial: Boolean = false
) : PlaywrightConfig<Locator.DblclickOptions>, LocatorOperation {
    override fun process(locator: Locator): Unit = locator.dblclick(toPlaywright())

    override fun toPlaywright(): Locator.DblclickOptions = Locator.DblclickOptions().also { options ->
        options.setButton(button)
        options.setDelay(delay)
        options.setForce(force)
        modifiers?.let { options.setModifiers(it) }
        position?.let { options.setPosition(it.x, it.y) }
        options.setTimeout(timeout)
        options.setTrial(trial)
    }
}