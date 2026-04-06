package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultZeroDouble

@GeneratedDsl
class KeyOperation(
    @DefaultZeroDouble
    val delay: Double = 0.0,
    @DefaultValue("30000.0")
    val timeout: Double = 30000.0
) : LocatorOperation {
    @TransientDsl
    internal var text: String? = null

    @TransientDsl
    internal var type: Type? = null

    override fun process(locator: Locator) {
        when (type) {
            Type.PRESS -> locator.press(text, pressOption())
            Type.PRESS_SEQUENTIALLY -> locator.press(text, pressSequentiallyOption())
            else -> Unit
        }
    }

    private fun pressOption(): Locator.PressOptions = Locator.PressOptions().also {
        it.delay = delay
        it.timeout = timeout
    }

    private fun pressSequentiallyOption(): Locator.PressOptions = Locator.PressOptions().also {
        it.delay = delay
        it.timeout = timeout
    }

    enum class Type {
        PRESS,
        PRESS_SEQUENTIALLY
    }
}