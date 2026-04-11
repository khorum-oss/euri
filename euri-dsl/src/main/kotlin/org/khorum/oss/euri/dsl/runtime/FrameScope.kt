package org.khorum.oss.euri.dsl.runtime

import com.microsoft.playwright.Frame
import org.khorum.oss.euri.dsl.common.EuriDsl
import org.khorum.oss.euri.dsl.enums.Role
import org.khorum.oss.euri.dsl.runtime.locator.LocatorOperation
import org.khorum.oss.konstellation.metaDsl.annotation.InjectDslMethod

@EuriDsl
class FrameScope(private val frame: Frame) {

    @InjectDslMethod
    fun locator(selector: String, block: LocatorScope.() -> Unit) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(frame.locator(selector))
    }

    @Suppress("LongParameterList")
    @InjectDslMethod
    fun getByRole(
        role: Role,
        checked: Boolean? = null,
        disabled: Boolean? = null,
        exact: Boolean? = null,
        expanded: Boolean? = null,
        includeHidden: Boolean? = null,
        level: Int? = null,
        name: String? = null,
        pressed: Boolean? = null,
        selected: Boolean? = null,
        block: LocatorScope.() -> Unit = {}
    ) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        val roleOptions = Frame.GetByRoleOptions()
        checked?.let { roleOptions.setChecked(it) }
        disabled?.let { roleOptions.setDisabled(it) }
        exact?.let { roleOptions.setExact(it) }
        expanded?.let { roleOptions.setExpanded(it) }
        includeHidden?.let { roleOptions.setIncludeHidden(it) }
        level?.let { roleOptions.setLevel(it) }
        name?.let { roleOptions.setName(it) }
        pressed?.let { roleOptions.setPressed(it) }
        selected?.let { roleOptions.setSelected(it) }
        scope.process(frame.getByRole(role.toPlaywright(), roleOptions))
    }

    @InjectDslMethod
    fun getByText(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(frame.getByText(text, Frame.GetByTextOptions().setExact(exact)))
    }

    @InjectDslMethod
    fun getByLabel(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(frame.getByLabel(text, Frame.GetByLabelOptions().setExact(exact)))
    }

    @InjectDslMethod
    fun getByPlaceholder(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(frame.getByPlaceholder(text, Frame.GetByPlaceholderOptions().setExact(exact)))
    }

    @InjectDslMethod
    fun getByTestId(testId: String, block: LocatorScope.() -> Unit = {}) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(frame.getByTestId(testId))
    }

    @InjectDslMethod
    fun getByAltText(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(frame.getByAltText(text, Frame.GetByAltTextOptions().setExact(exact)))
    }

    @InjectDslMethod
    fun getByTitle(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(frame.getByTitle(text, Frame.GetByTitleOptions().setExact(exact)))
    }

    @InjectDslMethod
    fun evaluate(expression: String): Any? = frame.evaluate(expression)

    @InjectDslMethod
    fun evaluate(expression: String, arg: Any?): Any? = frame.evaluate(expression, arg)

    @InjectDslMethod
    fun content(): String = frame.content()

    @InjectDslMethod
    fun url(): String = frame.url()

    @InjectDslMethod
    fun name(): String = frame.name()

    val raw: Frame get() = frame
}
