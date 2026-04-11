package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.runtime.LocatorScope
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse

@GeneratedDsl
class GetByTitleOperation(
    @DefaultFalse
    val exact: Boolean = false
) : LocatorOperation {
    @TransientDsl
    internal var text: String? = null

    @TransientDsl
    internal var childScope: LocatorScope? = null

    override fun process(locator: Locator) {
        val options = Locator.GetByTitleOptions().setExact(exact)
        val narrowed = locator.getByTitle(text, options)
        childScope?.process(narrowed)
    }
}
