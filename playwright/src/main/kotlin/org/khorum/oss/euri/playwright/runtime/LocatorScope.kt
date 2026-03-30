package org.khorum.oss.euri.playwright.runtime

import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.AriaRole
import org.khorum.oss.euri.dsl.common.EuriDsl
import java.util.regex.Pattern

@EuriDsl
class LocatorScope(private val locator: Locator) {

    // Actions
    fun click(block: (Locator.ClickOptions.() -> Unit)? = null) {
        val options = Locator.ClickOptions()
        block?.invoke(options)
        locator.click(options)
    }

    fun dblclick(block: (Locator.DblclickOptions.() -> Unit)? = null) {
        val options = Locator.DblclickOptions()
        block?.invoke(options)
        locator.dblclick(options)
    }

    fun fill(value: String, block: (Locator.FillOptions.() -> Unit)? = null) {
        val options = Locator.FillOptions()
        block?.invoke(options)
        locator.fill(value, options)
    }

    fun press(key: String, block: (Locator.PressOptions.() -> Unit)? = null) {
        val options = Locator.PressOptions()
        block?.invoke(options)
        locator.press(key, options)
    }

    fun pressSequentially(text: String, block: (Locator.PressSequentiallyOptions.() -> Unit)? = null) {
        val options = Locator.PressSequentiallyOptions()
        block?.invoke(options)
        locator.pressSequentially(text, options)
    }

    fun check(block: (Locator.CheckOptions.() -> Unit)? = null) {
        val options = Locator.CheckOptions()
        block?.invoke(options)
        locator.check(options)
    }

    fun uncheck(block: (Locator.UncheckOptions.() -> Unit)? = null) {
        val options = Locator.UncheckOptions()
        block?.invoke(options)
        locator.uncheck(options)
    }

    fun hover(block: (Locator.HoverOptions.() -> Unit)? = null) {
        val options = Locator.HoverOptions()
        block?.invoke(options)
        locator.hover(options)
    }

    fun focus() = locator.focus()

    fun blur(block: (Locator.BlurOptions.() -> Unit)? = null) {
        val options = Locator.BlurOptions()
        block?.invoke(options)
        locator.blur(options)
    }

    fun clear(block: (Locator.ClearOptions.() -> Unit)? = null) {
        val options = Locator.ClearOptions()
        block?.invoke(options)
        locator.clear(options)
    }

    fun selectOption(value: String): List<String> =
        locator.selectOption(value)

    fun selectOption(values: Array<String>): List<String> =
        locator.selectOption(values)

    fun setInputFiles(files: Array<java.nio.file.Path>) =
        locator.setInputFiles(files)

    fun dragTo(target: LocatorScope, block: (Locator.DragToOptions.() -> Unit)? = null) {
        val options = Locator.DragToOptions()
        block?.invoke(options)
        locator.dragTo(target.raw, options)
    }

    // Chaining
    fun locator(selector: String): LocatorScope =
        LocatorScope(locator.locator(selector))

    fun filter(block: Locator.FilterOptions.() -> Unit): LocatorScope {
        val options = Locator.FilterOptions()
        block(options)
        return LocatorScope(locator.filter(options))
    }

    fun first(): LocatorScope = LocatorScope(locator.first())

    fun last(): LocatorScope = LocatorScope(locator.last())

    fun nth(index: Int): LocatorScope = LocatorScope(locator.nth(index))

    fun and(other: LocatorScope): LocatorScope = LocatorScope(locator.and(other.raw))

    fun or(other: LocatorScope): LocatorScope = LocatorScope(locator.or(other.raw))

    fun getByRole(role: AriaRole, block: (Locator.GetByRoleOptions.() -> Unit)? = null): LocatorScope {
        val options = Locator.GetByRoleOptions()
        block?.invoke(options)
        return LocatorScope(locator.getByRole(role, options))
    }

    fun getByText(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(locator.getByText(text, Locator.GetByTextOptions().setExact(exact)))

    fun getByText(text: Pattern): LocatorScope =
        LocatorScope(locator.getByText(text))

    fun getByLabel(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(locator.getByLabel(text, Locator.GetByLabelOptions().setExact(exact)))

    fun getByPlaceholder(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(locator.getByPlaceholder(text, Locator.GetByPlaceholderOptions().setExact(exact)))

    fun getByTestId(testId: String): LocatorScope =
        LocatorScope(locator.getByTestId(testId))

    fun getByAltText(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(locator.getByAltText(text, Locator.GetByAltTextOptions().setExact(exact)))

    fun getByTitle(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(locator.getByTitle(text, Locator.GetByTitleOptions().setExact(exact)))

    // State queries
    fun isVisible(block: (Locator.IsVisibleOptions.() -> Unit)? = null): Boolean {
        val options = Locator.IsVisibleOptions()
        block?.invoke(options)
        return locator.isVisible(options)
    }

    fun isHidden(block: (Locator.IsHiddenOptions.() -> Unit)? = null): Boolean {
        val options = Locator.IsHiddenOptions()
        block?.invoke(options)
        return locator.isHidden(options)
    }

    fun isEnabled(block: (Locator.IsEnabledOptions.() -> Unit)? = null): Boolean {
        val options = Locator.IsEnabledOptions()
        block?.invoke(options)
        return locator.isEnabled(options)
    }

    fun isDisabled(block: (Locator.IsDisabledOptions.() -> Unit)? = null): Boolean {
        val options = Locator.IsDisabledOptions()
        block?.invoke(options)
        return locator.isDisabled(options)
    }

    fun isChecked(block: (Locator.IsCheckedOptions.() -> Unit)? = null): Boolean {
        val options = Locator.IsCheckedOptions()
        block?.invoke(options)
        return locator.isChecked(options)
    }

    fun isEditable(block: (Locator.IsEditableOptions.() -> Unit)? = null): Boolean {
        val options = Locator.IsEditableOptions()
        block?.invoke(options)
        return locator.isEditable(options)
    }

    fun count(): Int = locator.count()

    // Content
    fun innerText(block: (Locator.InnerTextOptions.() -> Unit)? = null): String {
        val options = Locator.InnerTextOptions()
        block?.invoke(options)
        return locator.innerText(options)
    }

    fun innerHTML(block: (Locator.InnerHTMLOptions.() -> Unit)? = null): String {
        val options = Locator.InnerHTMLOptions()
        block?.invoke(options)
        return locator.innerHTML(options)
    }

    fun inputValue(block: (Locator.InputValueOptions.() -> Unit)? = null): String {
        val options = Locator.InputValueOptions()
        block?.invoke(options)
        return locator.inputValue(options)
    }

    fun getAttribute(name: String, block: (Locator.GetAttributeOptions.() -> Unit)? = null): String? {
        val options = Locator.GetAttributeOptions()
        block?.invoke(options)
        return locator.getAttribute(name, options)
    }

    fun textContent(block: (Locator.TextContentOptions.() -> Unit)? = null): String? {
        val options = Locator.TextContentOptions()
        block?.invoke(options)
        return locator.textContent(options)
    }

    fun allInnerTexts(): List<String> = locator.allInnerTexts()

    fun allTextContents(): List<String> = locator.allTextContents()

    // Screenshot
    fun screenshot(block: Locator.ScreenshotOptions.() -> Unit): ByteArray {
        val options = Locator.ScreenshotOptions()
        block(options)
        return locator.screenshot(options)
    }

    val raw: Locator get() = locator
}
