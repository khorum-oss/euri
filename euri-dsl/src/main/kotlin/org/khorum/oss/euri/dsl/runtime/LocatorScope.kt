package org.khorum.oss.euri.dsl.runtime

import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.options.MouseButton
import org.khorum.oss.euri.dsl.runtime.locator.BlurOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.BlurOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.CheckableOperation
import org.khorum.oss.euri.dsl.runtime.locator.CheckableOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.CheckableOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.ClearOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.ClearOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.ClickOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.ClickOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.DoubleClickOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.DoubleClickOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.DragToOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.DragToOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.FillOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.FillOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.FilterOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.FilterOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.FocusOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.FocusOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.HoverOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.HoverOperationDslBuilderGroupScope
import org.khorum.oss.euri.dsl.runtime.locator.HoverOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.KeyOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.KeyOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.LocatorOperation
import org.khorum.oss.euri.dsl.runtime.locator.SelectOptionOperation
import org.khorum.oss.euri.dsl.runtime.locator.SelectOptionOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.SelectOptionOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.SelectorOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.SelectorOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.SetInputFilesOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.SetInputFilesOperationDslBuilderScope
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl
import java.nio.file.Path
import java.util.regex.Pattern

@GeneratedDsl
class LocatorScope(
    @ListDsl(withVararg = false, withProvider = false)
    private val options: MutableList<LocatorOperation>
) : LocatorOperation {
    @TransientDsl
    internal var locator: Locator? = null

    // Actions
    fun click(button: MouseButton = MouseButton.LEFT, block: ClickOperationDslBuilderScope) {
        val option = ClickOperationDslBuilder().apply(block).build()
        option.button = button
        options.add(option)
    }

    fun dblclick(button: MouseButton = MouseButton.LEFT, block: DoubleClickOperationDslBuilderScope) {
        val option = DoubleClickOperationDslBuilder().apply(block).build()
        option.button = button
        options.add(option)
    }

    fun fill(value: String, block: FillOperationDslBuilderScope? = null) {
        val optionBuilder = FillOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        option.value = value
        options.add(option)
    }

    fun press(key: String, block: KeyOperationDslBuilderScope? = null) {
        val optionBuilder = KeyOperationDslBuilder()

        block?.invoke(optionBuilder)

        val option = optionBuilder.build()

        option.text = key

        options.add(option)
    }

    fun pressSequentially(text: String, block: KeyOperationDslBuilderScope? = null) {
        val optionBuilder = KeyOperationDslBuilder()

        block?.invoke(optionBuilder)

        val option = optionBuilder.build()

        option.text = text

        options.add(option)
    }

    fun check(block: CheckableOperationDslBuilderScope) {
        val option = CheckableOperationDslBuilder().apply(block).build()
        option.type = CheckableOperation.Type.CHECKED
        options.add(option)
    }

    fun uncheck(block: CheckableOperationDslBuilderScope) {
        val option = CheckableOperationDslBuilder().apply(block).build()
        option.type = CheckableOperation.Type.UNCHECKED
        options.add(option)
    }

    fun hover(block: HoverOperationDslBuilderScope) {
        val option = HoverOperationDslBuilder().apply(block).build()
        options.add(option)
    }

    fun focus(block: FocusOperationDslBuilderScope) {
        val option = FocusOperationDslBuilder().apply(block).build()
        options.add(option)
    }

    fun blur(block: BlurOperationDslBuilderScope) {
        val option = BlurOperationDslBuilder().apply(block).build()
        options.add(option)
    }

    fun clear(block: ClearOperationDslBuilderScope) {
        val option = ClearOperationDslBuilder().apply(block).build()
        options.add(option)
    }

    fun selectOption(value: String, block: SelectOptionOperationDslBuilderScope? = null) {
        val optionBuilder = SelectOptionOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        option.values = listOf(value)
        options.add(option)
    }

    fun selectOption(values: List<String>, block: SelectOptionOperationDslBuilderScope? = null) {
        val optionBuilder = SelectOptionOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        option.values = values
        options.add(option)
    }

    fun setInputFilesByPath(files: List<Path>, block: SetInputFilesOperationDslBuilderScope? = null) {
        val optionBuilder = SetInputFilesOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        option.files = files.toTypedArray()
        options.add(option)
    }

    fun setInputFilesByFilename(fileName: List<String>, block: SetInputFilesOperationDslBuilderScope? = null) {
        val optionBuilder = SetInputFilesOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        option.files = fileName.map { Path.of(it) }.toTypedArray()
        options.add(option)
    }

    fun dragTo(target: LocatorScope, block: DragToOperationDslBuilderScope? = null) {
        val optionBuilder = DragToOperationDslBuilder()
        block?.let { optionBuilder.apply(it) }
        val option = optionBuilder.build()
        option.targetScope = target
        options.add(option)
    }

    // Chaining
    fun locator(selector: String, block: SelectorOperationDslBuilderScope? = null) {
        val optionBuilder = SelectorOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        option.selector = selector
        options.add(option)
    }

    fun filter(block: FilterOperationDslBuilderScope? = null) {
        val optionBuilder = FilterOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        options.add(option)
    }
//
//    fun first(): LocatorScope {
//        options.filterIsInstance<LocatorScope>().first()
//    }
//
//    fun last(): LocatorScope = LocatorScope(locator.last())
//
//    fun nth(index: Int): LocatorScope = LocatorScope(locator.nth(index))
//
//    fun and(other: LocatorScope): LocatorScope = LocatorScope(locator.and(other.raw))
//
//    fun or(other: LocatorScope): LocatorScope = LocatorScope(locator.or(other.raw))
//
//    fun getByRole(role: AriaRole, block: (Locator.GetByRoleOptions.() -> Unit)? = null): LocatorScope {
//        val options = Locator.GetByRoleOptions()
//        block?.invoke(options)
//        return LocatorScope(locator.getByRole(role, options))
//    }
//
//    fun getByText(text: String, exact: Boolean = false): LocatorScope =
//        LocatorScope(locator.getByText(text, Locator.GetByTextOptions().setExact(exact)))
//
//    fun getByText(text: Pattern): LocatorScope =
//        LocatorScope(locator.getByText(text))
//
//    fun getByLabel(text: String, exact: Boolean = false): LocatorScope =
//        LocatorScope(locator.getByLabel(text, Locator.GetByLabelOptions().setExact(exact)))
//
//    fun getByPlaceholder(text: String, exact: Boolean = false): LocatorScope =
//        LocatorScope(locator.getByPlaceholder(text, Locator.GetByPlaceholderOptions().setExact(exact)))
//
//    fun getByTestId(testId: String): LocatorScope =
//        LocatorScope(locator.getByTestId(testId))
//
//    fun getByAltText(text: String, exact: Boolean = false): LocatorScope =
//        LocatorScope(locator.getByAltText(text, Locator.GetByAltTextOptions().setExact(exact)))
//
//    fun getByTitle(text: String, exact: Boolean = false): LocatorScope =
//        LocatorScope(locator.getByTitle(text, Locator.GetByTitleOptions().setExact(exact)))

    // State queries
//    fun isVisible(block: (Locator.IsVisibleOptions.() -> Unit)? = null): Boolean {
//        val options = Locator.IsVisibleOptions()
//        block?.invoke(options)
//        return locator.isVisible(options)
//    }
//
//    fun isHidden(block: (Locator.IsHiddenOptions.() -> Unit)? = null): Boolean {
//        val options = Locator.IsHiddenOptions()
//        block?.invoke(options)
//        return locator.isHidden(options)
//    }
//
//    fun isEnabled(block: (Locator.IsEnabledOptions.() -> Unit)? = null): Boolean {
//        val options = Locator.IsEnabledOptions()
//        block?.invoke(options)
//        return locator.isEnabled(options)
//    }
//
//    fun isDisabled(block: (Locator.IsDisabledOptions.() -> Unit)? = null): Boolean {
//        val options = Locator.IsDisabledOptions()
//        block?.invoke(options)
//        return locator.isDisabled(options)
//    }
//
//    fun isChecked(block: (Locator.IsCheckedOptions.() -> Unit)? = null): Boolean {
//        val options = Locator.IsCheckedOptions()
//        block?.invoke(options)
//        return locator.isChecked(options)
//    }
//
//    fun isEditable(block: (Locator.IsEditableOptions.() -> Unit)? = null): Boolean {
//        val options = Locator.IsEditableOptions()
//        block?.invoke(options)
//        return locator.isEditable(options)
//    }
//
//    fun count(): Int = locator.count()
//
//    // Content
//    fun innerText(block: (Locator.InnerTextOptions.() -> Unit)? = null): String {
//        val options = Locator.InnerTextOptions()
//        block?.invoke(options)
//        return locator.innerText(options)
//    }
//
//    fun innerHTML(block: (Locator.InnerHTMLOptions.() -> Unit)? = null): String {
//        val options = Locator.InnerHTMLOptions()
//        block?.invoke(options)
//        return locator.innerHTML(options)
//    }
//
//    fun inputValue(block: (Locator.InputValueOptions.() -> Unit)? = null): String {
//        val options = Locator.InputValueOptions()
//        block?.invoke(options)
//        return locator.inputValue(options)
//    }
//
//    fun getAttribute(name: String, block: (Locator.GetAttributeOptions.() -> Unit)? = null): String? {
//        val options = Locator.GetAttributeOptions()
//        block?.invoke(options)
//        return locator.getAttribute(name, options)
//    }
//
//    fun textContent(block: (Locator.TextContentOptions.() -> Unit)? = null): String? {
//        val options = Locator.TextContentOptions()
//        block?.invoke(options)
//        return locator.textContent(options)
//    }
//
//    fun allInnerTexts(): List<String> = locator.allInnerTexts()
//
//    fun allTextContents(): List<String> = locator.allTextContents()

    // Screenshot
//    fun screenshot(block: Locator.ScreenshotOptions.() -> Unit): ByteArray {
//        val options = Locator.ScreenshotOptions()
//        block(options)
//        return locator.screenshot(options)
//    }

    override fun process(locator: Locator) {
         options.forEach { it.process(locator) }
    }
}
