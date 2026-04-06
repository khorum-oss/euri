package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.euri.dsl.runtime.LocatorScope
import org.khorum.oss.euri.dsl.runtime.PlaywrightPosition
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DO_NOT

@GeneratedDsl
class DragToOperation(
    @DefaultFalse(negationTemplate = DO_NOT)
    val force: Boolean = false,
    private val sourcePosition: PlaywrightPosition? = null,
    private val targetPosition: PlaywrightPosition? = null,
    @DefaultValue("30000.0")
    val timeout: Double = 30000.0,
    @DefaultFalse
    val trial: Boolean = false
) : PlaywrightConfig<Locator.DragToOptions>, LocatorOperation {
    @TransientDsl
    internal var targetScope: LocatorScope? = null

    override fun process(locator: Locator) {
        val targetScope = requireNotNull(targetScope) { "Target scope is not set" }
        locator.dragTo(targetScope.resolvedLocator, toPlaywright())
    }

    override fun toPlaywright(): Locator.DragToOptions = Locator.DragToOptions().also { options ->
        options.setForce(force)
        sourcePosition?.let { options.setSourcePosition(it.x, it.y) }
        targetPosition?.let { options.setTargetPosition(it.x, it.y) }
        options.setTimeout(timeout)
        options.setTrial(trial)
    }
}