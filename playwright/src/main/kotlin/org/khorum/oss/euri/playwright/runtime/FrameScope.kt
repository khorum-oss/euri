package org.khorum.oss.euri.playwright.runtime

import com.microsoft.playwright.Frame
import org.khorum.oss.euri.dsl.common.EuriDsl
import org.khorum.oss.euri.playwright.enums.Role
import org.khorum.oss.konstellation.metaDsl.annotation.InjectDslMethod

@EuriDsl
class FrameScope(private val frame: Frame) {

    @InjectDslMethod
    fun locator(selector: String): LocatorScope =
        LocatorScope(frame.locator(selector))

    @InjectDslMethod
    fun getByRole(role: Role, block: (Frame.GetByRoleOptions.() -> Unit)? = null): LocatorScope {
        val options = Frame.GetByRoleOptions()
        block?.invoke(options)
        return LocatorScope(frame.getByRole(role.toPlaywright(), options))
    }

    @InjectDslMethod
    fun getByText(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(frame.getByText(text, Frame.GetByTextOptions().setExact(exact)))

    @InjectDslMethod
    fun getByLabel(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(frame.getByLabel(text, Frame.GetByLabelOptions().setExact(exact)))

    @InjectDslMethod
    fun getByPlaceholder(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(frame.getByPlaceholder(text, Frame.GetByPlaceholderOptions().setExact(exact)))

    @InjectDslMethod
    fun getByTestId(testId: String): LocatorScope =
        LocatorScope(frame.getByTestId(testId))

    @InjectDslMethod
    fun getByAltText(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(frame.getByAltText(text, Frame.GetByAltTextOptions().setExact(exact)))

    @InjectDslMethod
    fun getByTitle(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(frame.getByTitle(text, Frame.GetByTitleOptions().setExact(exact)))

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
