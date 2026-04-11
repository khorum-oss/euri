package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.enums.Role
import org.khorum.oss.euri.dsl.runtime.LocatorScope
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl

@GeneratedDsl
class GetByRoleOperation(
    val checked: Boolean? = null,
    val disabled: Boolean? = null,
    val exact: Boolean? = null,
    val expanded: Boolean? = null,
    val includeHidden: Boolean? = null,
    val level: Int? = null,
    val name: String? = null,
    val pressed: Boolean? = null,
    val selected: Boolean? = null
) : LocatorOperation {
    @TransientDsl
    internal var role: Role? = null

    @TransientDsl
    internal var childScope: LocatorScope? = null

    override fun process(locator: Locator) {
        val options = Locator.GetByRoleOptions()
        checked?.let { options.setChecked(it) }
        disabled?.let { options.setDisabled(it) }
        exact?.let { options.setExact(it) }
        expanded?.let { options.setExpanded(it) }
        includeHidden?.let { options.setIncludeHidden(it) }
        level?.let { options.setLevel(it) }
        name?.let { options.setName(it) }
        pressed?.let { options.setPressed(it) }
        selected?.let { options.setSelected(it) }

        val narrowed = locator.getByRole(role?.toPlaywright(), options)
        childScope?.process(narrowed)
    }
}
