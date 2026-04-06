//package org.khorum.oss.euri.dsl.runtime
//
//import com.microsoft.playwright.Frame
//import com.microsoft.playwright.options.AriaRole
//import org.khorum.oss.euri.dsl.common.EuriDsl
//import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
//
//@GeneratedDsl("frame")
//class FrameScope(
//    private val locator: LocatorScope,
//) {
//    private val frame: Frame = Any as Frame
//
//    fun locator(selector: String): LocatorScope =
//        LocatorScope(frame.locator(selector))
//
//    fun getByRole(role: AriaRole, block: (Frame.GetByRoleOptions.() -> Unit)? = null): LocatorScope {
//        val options = Frame.GetByRoleOptions()
//        block?.invoke(options)
//        return LocatorScope(frame.getByRole(role, options))
//    }
//
//    fun getByText(text: String, exact: Boolean = false): LocatorScope =
//        LocatorScope(frame.getByText(text, Frame.GetByTextOptions().setExact(exact)))
//
//    fun getByLabel(text: String, exact: Boolean = false): LocatorScope =
//        LocatorScope(frame.getByLabel(text, Frame.GetByLabelOptions().setExact(exact)))
//
//    fun getByPlaceholder(text: String, exact: Boolean = false): LocatorScope =
//        LocatorScope(frame.getByPlaceholder(text, Frame.GetByPlaceholderOptions().setExact(exact)))
//
//    fun getByTestId(testId: String): LocatorScope =
//        LocatorScope(frame.getByTestId(testId))
//
//    fun getByAltText(text: String, exact: Boolean = false): LocatorScope =
//        LocatorScope(frame.getByAltText(text, Frame.GetByAltTextOptions().setExact(exact)))
//
//    fun getByTitle(text: String, exact: Boolean = false): LocatorScope =
//        LocatorScope(frame.getByTitle(text, Frame.GetByTitleOptions().setExact(exact)))
//
//    fun evaluate(expression: String): Any? = frame.evaluate(expression)
//
//    fun evaluate(expression: String, arg: Any?): Any? = frame.evaluate(expression, arg)
//
//    fun content(): String = frame.content()
//
//    fun url(): String = frame.url()
//
//    fun name(): String = frame.name()
//
//    val raw: Frame get() = frame
//}
