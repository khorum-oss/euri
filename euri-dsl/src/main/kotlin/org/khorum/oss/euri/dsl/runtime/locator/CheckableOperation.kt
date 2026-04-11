package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.euri.dsl.runtime.PlaywrightPosition
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DO_NOT

@GeneratedDsl
class CheckableOperation(
    @DefaultFalse(negationTemplate = DO_NOT)
    val force: Boolean = false,
    private val position: PlaywrightPosition? = null,
    @DefaultValue("30000.0")
    val timeout: Double = 30000.0,
    @DefaultFalse
    val trial: Boolean = false
) : LocatorOperation {
    @TransientDsl
    internal var type: Type? = null

    override fun process(locator: Locator): Unit = when (type) {
        Type.CHECKED -> locator.check(toCheckOption())
        Type.UNCHECKED -> locator.uncheck(toUncheckOption())
        else -> Unit
    }

    fun toCheckOption(): Locator.CheckOptions = Locator.CheckOptions().also { options ->
        position?.let { options.setPosition(it.x, it.y) }
        options.setTimeout(timeout)
        options.setForce(force)
        options.setTrial(trial)
    }

    fun toUncheckOption(): Locator.UncheckOptions = Locator.UncheckOptions().also { options ->
        position?.let { options.setPosition(it.x, it.y) }
        options.setTimeout(timeout)
        options.setForce(force)
    }

    enum class Type {
        CHECKED,
        UNCHECKED
    }
}