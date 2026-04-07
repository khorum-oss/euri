package org.khorum.oss.euri.playwright.runtime

import com.microsoft.playwright.Locator
import org.khorum.oss.euri.dsl.common.EuriDsl
import org.khorum.oss.euri.playwright.enums.Role
import org.khorum.oss.konstellation.metaDsl.annotation.InjectDslMethod
import java.util.regex.Pattern

@EuriDsl
class LocatorScope(private val locator: Locator) {

    // Actions
    @InjectDslMethod
    fun click(block: (Locator.ClickOptions.() -> Unit)? = null) {
        val options = Locator.ClickOptions()
        block?.invoke(options)
        locator.click(options)
    }

    @InjectDslMethod
    fun dblclick(block: (Locator.DblclickOptions.() -> Unit)? = null) {
        val options = Locator.DblclickOptions()
        block?.invoke(options)
        locator.dblclick(options)
    }

    @InjectDslMethod
    fun fill(value: String, block: (Locator.FillOptions.() -> Unit)? = null) {
        val options = Locator.FillOptions()
        block?.invoke(options)
        locator.fill(value, options)
    }

    @InjectDslMethod
    fun press(key: String, block: (Locator.PressOptions.() -> Unit)? = null) {
        val options = Locator.PressOptions()
        block?.invoke(options)
        locator.press(key, options)
    }

    @InjectDslMethod
    fun pressSequentially(text: String, block: (Locator.PressSequentiallyOptions.() -> Unit)? = null) {
        val options = Locator.PressSequentiallyOptions()
        block?.invoke(options)
        locator.pressSequentially(text, options)
    }

    @InjectDslMethod
    fun check(block: (Locator.CheckOptions.() -> Unit)? = null) {
        val options = Locator.CheckOptions()
        block?.invoke(options)
        locator.check(options)
    }

    @InjectDslMethod
    fun uncheck(block: (Locator.UncheckOptions.() -> Unit)? = null) {
        val options = Locator.UncheckOptions()
        block?.invoke(options)
        locator.uncheck(options)
    }

    @InjectDslMethod
    fun hover(block: (Locator.HoverOptions.() -> Unit)? = null) {
        val options = Locator.HoverOptions()
        block?.invoke(options)
        locator.hover(options)
    }

    @InjectDslMethod
    fun focus() = locator.focus()

    @InjectDslMethod
    fun blur(block: (Locator.BlurOptions.() -> Unit)? = null) {
        val options = Locator.BlurOptions()
        block?.invoke(options)
        locator.blur(options)
    }

    @InjectDslMethod
    fun clear(block: (Locator.ClearOptions.() -> Unit)? = null) {
        val options = Locator.ClearOptions()
        block?.invoke(options)
        locator.clear(options)
    }

    @InjectDslMethod
    fun selectOption(value: String): List<String> =
        locator.selectOption(value)

    @InjectDslMethod
    fun selectOption(values: Array<String>): List<String> =
        locator.selectOption(values)

    @InjectDslMethod
    fun setInputFiles(files: Array<java.nio.file.Path>) =
        locator.setInputFiles(files)

    @InjectDslMethod
    fun dragTo(target: LocatorScope, block: (Locator.DragToOptions.() -> Unit)? = null) {
        val options = Locator.DragToOptions()
        block?.invoke(options)
        locator.dragTo(target.raw, options)
    }

    // Chaining
    @InjectDslMethod
    fun locator(selector: String): LocatorScope =
        LocatorScope(locator.locator(selector))

    @InjectDslMethod
    fun filter(block: Locator.FilterOptions.() -> Unit): LocatorScope {
        val options = Locator.FilterOptions()
        block(options)
        return LocatorScope(locator.filter(options))
    }

    @InjectDslMethod
    fun first(): LocatorScope = LocatorScope(locator.first())

    @InjectDslMethod
    fun last(): LocatorScope = LocatorScope(locator.last())

    @InjectDslMethod
    fun nth(index: Int): LocatorScope = LocatorScope(locator.nth(index))

    @InjectDslMethod
    fun and(other: LocatorScope): LocatorScope = LocatorScope(locator.and(other.raw))

    @InjectDslMethod
    fun or(other: LocatorScope): LocatorScope = LocatorScope(locator.or(other.raw))

    @InjectDslMethod
    fun getByRole(role: Role, block: (Locator.GetByRoleOptions.() -> Unit)? = null): LocatorScope {
        val options = Locator.GetByRoleOptions()
        block?.invoke(options)
        return LocatorScope(locator.getByRole(role.toPlaywright(), options))
    }

    @InjectDslMethod
    fun getByText(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(locator.getByText(text, Locator.GetByTextOptions().setExact(exact)))

    @InjectDslMethod
    fun getByText(text: Pattern): LocatorScope =
        LocatorScope(locator.getByText(text))

    @InjectDslMethod
    fun getByLabel(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(locator.getByLabel(text, Locator.GetByLabelOptions().setExact(exact)))

    @InjectDslMethod
    fun getByPlaceholder(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(locator.getByPlaceholder(text, Locator.GetByPlaceholderOptions().setExact(exact)))

    @InjectDslMethod
    fun getByTestId(testId: String): LocatorScope =
        LocatorScope(locator.getByTestId(testId))

    @InjectDslMethod
    fun getByAltText(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(locator.getByAltText(text, Locator.GetByAltTextOptions().setExact(exact)))

    @InjectDslMethod
    fun getByTitle(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(locator.getByTitle(text, Locator.GetByTitleOptions().setExact(exact)))

    // State queries
    @InjectDslMethod
    fun isVisible(block: (Locator.IsVisibleOptions.() -> Unit)? = null): Boolean {
        val options = Locator.IsVisibleOptions()
        block?.invoke(options)
        return locator.isVisible(options)
    }

    @InjectDslMethod
    fun isHidden(block: (Locator.IsHiddenOptions.() -> Unit)? = null): Boolean {
        val options = Locator.IsHiddenOptions()
        block?.invoke(options)
        return locator.isHidden(options)
    }

    @InjectDslMethod
    fun isEnabled(block: (Locator.IsEnabledOptions.() -> Unit)? = null): Boolean {
        val options = Locator.IsEnabledOptions()
        block?.invoke(options)
        return locator.isEnabled(options)
    }

    @InjectDslMethod
    fun isDisabled(block: (Locator.IsDisabledOptions.() -> Unit)? = null): Boolean {
        val options = Locator.IsDisabledOptions()
        block?.invoke(options)
        return locator.isDisabled(options)
    }

    @InjectDslMethod
    fun isChecked(block: (Locator.IsCheckedOptions.() -> Unit)? = null): Boolean {
        val options = Locator.IsCheckedOptions()
        block?.invoke(options)
        return locator.isChecked(options)
    }

    @InjectDslMethod
    fun isEditable(block: (Locator.IsEditableOptions.() -> Unit)? = null): Boolean {
        val options = Locator.IsEditableOptions()
        block?.invoke(options)
        return locator.isEditable(options)
    }

    @InjectDslMethod
    fun count(): Int = locator.count()

    // Content
    @InjectDslMethod
    fun innerText(block: (Locator.InnerTextOptions.() -> Unit)? = null): String {
        val options = Locator.InnerTextOptions()
        block?.invoke(options)
        return locator.innerText(options)
    }

    @InjectDslMethod
    fun innerHTML(block: (Locator.InnerHTMLOptions.() -> Unit)? = null): String {
        val options = Locator.InnerHTMLOptions()
        block?.invoke(options)
        return locator.innerHTML(options)
    }

    @InjectDslMethod
    fun inputValue(block: (Locator.InputValueOptions.() -> Unit)? = null): String {
        val options = Locator.InputValueOptions()
        block?.invoke(options)
        return locator.inputValue(options)
    }

    @InjectDslMethod
    fun getAttribute(name: String, block: (Locator.GetAttributeOptions.() -> Unit)? = null): String? {
        val options = Locator.GetAttributeOptions()
        block?.invoke(options)
        return locator.getAttribute(name, options)
    }

    @InjectDslMethod
    fun textContent(block: (Locator.TextContentOptions.() -> Unit)? = null): String? {
        val options = Locator.TextContentOptions()
        block?.invoke(options)
        return locator.textContent(options)
    }

    @InjectDslMethod
    fun allInnerTexts(): List<String> = locator.allInnerTexts()

    @InjectDslMethod
    fun allTextContents(): List<String> = locator.allTextContents()

    // Screenshot
    @InjectDslMethod
    fun screenshot(block: Locator.ScreenshotOptions.() -> Unit): ByteArray {
        val options = Locator.ScreenshotOptions()
        block(options)
        return locator.screenshot(options)
    }

    val raw: Locator get() = locator
}
