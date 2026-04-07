package org.khorum.oss.euri.dsl.runtime

import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.MouseButton
import org.khorum.oss.euri.dsl.enums.Role
import org.khorum.oss.euri.dsl.runtime.locator.AllInnerTextsOperation
import org.khorum.oss.euri.dsl.runtime.locator.AllTextContentsOperation
import org.khorum.oss.euri.dsl.runtime.locator.BlurOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.BlurOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.ChainOperation
import org.khorum.oss.euri.dsl.runtime.locator.CheckableOperation
import org.khorum.oss.euri.dsl.runtime.locator.CheckableOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.CheckableOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.ClearOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.ClearOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.ClickOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.ClickOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.CountOperation
import org.khorum.oss.euri.dsl.runtime.locator.DoubleClickOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.DoubleClickOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.DragToOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.DragToOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.FillOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.FillOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.FocusOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.FocusOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.GetAttributeOperation
import org.khorum.oss.euri.dsl.runtime.locator.GetAttributeOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.GetAttributeOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.GetByAltTextOperation
import org.khorum.oss.euri.dsl.runtime.locator.GetByLabelOperation
import org.khorum.oss.euri.dsl.runtime.locator.GetByPlaceholderOperation
import org.khorum.oss.euri.dsl.runtime.locator.GetByRoleOperation
import org.khorum.oss.euri.dsl.runtime.locator.GetByTextOperation
import org.khorum.oss.euri.dsl.runtime.locator.GetByTitleOperation
import org.khorum.oss.euri.dsl.runtime.locator.HoverOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.HoverOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.InnerHtmlOperation
import org.khorum.oss.euri.dsl.runtime.locator.InnerHtmlOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.InnerHtmlOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.InnerTextOperation
import org.khorum.oss.euri.dsl.runtime.locator.InnerTextOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.InnerTextOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.InputValueOperation
import org.khorum.oss.euri.dsl.runtime.locator.InputValueOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.InputValueOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.IsCheckedOperation
import org.khorum.oss.euri.dsl.runtime.locator.IsCheckedOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.IsCheckedOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.IsDisabledOperation
import org.khorum.oss.euri.dsl.runtime.locator.IsDisabledOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.IsDisabledOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.IsEditableOperation
import org.khorum.oss.euri.dsl.runtime.locator.IsEditableOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.IsEditableOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.IsEnabledOperation
import org.khorum.oss.euri.dsl.runtime.locator.IsEnabledOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.IsEnabledOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.IsHiddenOperation
import org.khorum.oss.euri.dsl.runtime.locator.IsHiddenOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.IsHiddenOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.IsVisibleOperation
import org.khorum.oss.euri.dsl.runtime.locator.IsVisibleOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.IsVisibleOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.KeyOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.KeyOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.LocatorOperation
import org.khorum.oss.euri.dsl.runtime.locator.SelectOptionOperation
import org.khorum.oss.euri.dsl.runtime.locator.SelectOptionOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.SelectOptionOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.SelectorOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.SetInputFilesOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.SetInputFilesOperationDslBuilderScope
import org.khorum.oss.euri.dsl.runtime.locator.TextContentOperation
import org.khorum.oss.euri.dsl.runtime.locator.TextContentOperationDslBuilder
import org.khorum.oss.euri.dsl.runtime.locator.TextContentOperationDslBuilderScope
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl
import java.nio.file.Path

@GeneratedDsl
class LocatorScope(
    @ListDsl(withVararg = false, withProvider = false)
    private val options: MutableList<LocatorOperation>
) : LocatorOperation {

    // ── Actions ──────────────────────────────────────────────────────────

    fun click(button: MouseButton = MouseButton.LEFT, block: ClickOperationDslBuilderScope) {
        val builder = ClickOperationDslBuilder()
        builder.button = button
        builder.apply(block)
        options.add(builder.build())
    }

    fun dblclick(button: MouseButton = MouseButton.LEFT, block: DoubleClickOperationDslBuilderScope) {
        val builder = DoubleClickOperationDslBuilder()
        builder.button = button
        builder.apply(block)
        options.add(builder.build())
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
        option.type = org.khorum.oss.euri.dsl.runtime.locator.KeyOperation.Type.PRESS
        options.add(option)
    }

    fun pressSequentially(text: String, block: KeyOperationDslBuilderScope? = null) {
        val optionBuilder = KeyOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        option.text = text
        option.type = org.khorum.oss.euri.dsl.runtime.locator.KeyOperation.Type.PRESS_SEQUENTIALLY
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

    fun selectOption(value: String, block: SelectOptionOperationDslBuilderScope? = null): SelectOptionOperation {
        val optionBuilder = SelectOptionOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        option.values = listOf(value)
        options.add(option)
        return option
    }

    fun selectOption(values: List<String>, block: SelectOptionOperationDslBuilderScope? = null): SelectOptionOperation {
        val optionBuilder = SelectOptionOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        option.values = values
        options.add(option)
        return option
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

    // ── Chaining: locator / filter ───────────────────────────────────────

    fun locator(selector: String, block: LocatorScope.() -> Unit) {
        val childScope = LocatorScope(mutableListOf()).apply(block)
        val option = SelectorOperationDslBuilder().build()
        option.selector = selector
        option.childScope = childScope
        options.add(option)
    }

    fun filter(
        hasText: Any? = null,
        hasNotText: Any? = null,
        block: LocatorScope.() -> Unit
    ) {
        val childScope = LocatorScope(mutableListOf()).apply(block)
        val option = org.khorum.oss.euri.dsl.runtime.locator.FilterOperation(
            hasText = hasText,
            hasNotText = hasNotText
        )
        option.childScope = childScope
        options.add(option)
    }

    // ── Chaining: positional ─────────────────────────────────────────────

    fun first(block: LocatorScope.() -> Unit) {
        val childScope = LocatorScope(mutableListOf()).apply(block)
        options.add(ChainOperation({ it.first() }, childScope))
    }

    fun last(block: LocatorScope.() -> Unit) {
        val childScope = LocatorScope(mutableListOf()).apply(block)
        options.add(ChainOperation({ it.last() }, childScope))
    }

    fun nth(index: Int, block: LocatorScope.() -> Unit) {
        val childScope = LocatorScope(mutableListOf()).apply(block)
        options.add(ChainOperation({ it.nth(index) }, childScope))
    }

    // ── Chaining: combinators ────────────────────────────────────────────

    fun and(other: LocatorScope, block: LocatorScope.() -> Unit) {
        val childScope = LocatorScope(mutableListOf()).apply(block)
        options.add(ChainOperation({ it.and(other.resolvedLocator!!) }, childScope))
    }

    fun or(other: LocatorScope, block: LocatorScope.() -> Unit) {
        val childScope = LocatorScope(mutableListOf()).apply(block)
        options.add(ChainOperation({ it.or(other.resolvedLocator!!) }, childScope))
    }

    // ── Chaining: getBy* ─────────────────────────────────────────────────

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
        val childScope = LocatorScope(mutableListOf()).apply(block)
        val operation = GetByRoleOperation(
            checked = checked,
            disabled = disabled,
            exact = exact,
            expanded = expanded,
            includeHidden = includeHidden,
            level = level,
            name = name,
            pressed = pressed,
            selected = selected
        )
        operation.role = role
        operation.childScope = childScope
        options.add(operation)
    }

    fun getByText(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val childScope = LocatorScope(mutableListOf()).apply(block)
        val operation = GetByTextOperation(exact = exact)
        operation.text = text
        operation.childScope = childScope
        options.add(operation)
    }

    fun getByLabel(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val childScope = LocatorScope(mutableListOf()).apply(block)
        val operation = GetByLabelOperation(exact = exact)
        operation.text = text
        operation.childScope = childScope
        options.add(operation)
    }

    fun getByPlaceholder(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val childScope = LocatorScope(mutableListOf()).apply(block)
        val operation = GetByPlaceholderOperation(exact = exact)
        operation.text = text
        operation.childScope = childScope
        options.add(operation)
    }

    fun getByTestId(testId: String, block: LocatorScope.() -> Unit = {}) {
        val childScope = LocatorScope(mutableListOf()).apply(block)
        options.add(ChainOperation({ it.getByTestId(testId) }, childScope))
    }

    fun getByAltText(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val childScope = LocatorScope(mutableListOf()).apply(block)
        val operation = GetByAltTextOperation(exact = exact)
        operation.text = text
        operation.childScope = childScope
        options.add(operation)
    }

    fun getByTitle(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val childScope = LocatorScope(mutableListOf()).apply(block)
        val operation = GetByTitleOperation(exact = exact)
        operation.text = text
        operation.childScope = childScope
        options.add(operation)
    }

    // ── State queries ────────────────────────────────────────────────────

    fun isVisible(block: IsVisibleOperationDslBuilderScope? = null): IsVisibleOperation {
        val optionBuilder = IsVisibleOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        options.add(option)
        return option
    }

    fun isHidden(block: IsHiddenOperationDslBuilderScope? = null): IsHiddenOperation {
        val optionBuilder = IsHiddenOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        options.add(option)
        return option
    }

    fun isEnabled(block: IsEnabledOperationDslBuilderScope? = null): IsEnabledOperation {
        val optionBuilder = IsEnabledOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        options.add(option)
        return option
    }

    fun isDisabled(block: IsDisabledOperationDslBuilderScope? = null): IsDisabledOperation {
        val optionBuilder = IsDisabledOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        options.add(option)
        return option
    }

    fun isChecked(block: IsCheckedOperationDslBuilderScope? = null): IsCheckedOperation {
        val optionBuilder = IsCheckedOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        options.add(option)
        return option
    }

    fun isEditable(block: IsEditableOperationDslBuilderScope? = null): IsEditableOperation {
        val optionBuilder = IsEditableOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        options.add(option)
        return option
    }

    fun count(): CountOperation {
        val option = CountOperation()
        options.add(option)
        return option
    }

    // ── Content queries ──────────────────────────────────────────────────

    fun innerText(block: InnerTextOperationDslBuilderScope? = null): InnerTextOperation {
        val optionBuilder = InnerTextOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        options.add(option)
        return option
    }

    fun innerHTML(block: InnerHtmlOperationDslBuilderScope? = null): InnerHtmlOperation {
        val optionBuilder = InnerHtmlOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        options.add(option)
        return option
    }

    fun inputValue(block: InputValueOperationDslBuilderScope? = null): InputValueOperation {
        val optionBuilder = InputValueOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        options.add(option)
        return option
    }

    fun getAttribute(name: String, block: GetAttributeOperationDslBuilderScope? = null): GetAttributeOperation {
        val optionBuilder = GetAttributeOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        option.name = name
        options.add(option)
        return option
    }

    fun textContent(block: TextContentOperationDslBuilderScope? = null): TextContentOperation {
        val optionBuilder = TextContentOperationDslBuilder()
        block?.invoke(optionBuilder)
        val option = optionBuilder.build()
        options.add(option)
        return option
    }

    fun allInnerTexts(): AllInnerTextsOperation {
        val option = AllInnerTextsOperation()
        options.add(option)
        return option
    }

    fun allTextContents(): AllTextContentsOperation {
        val option = AllTextContentsOperation()
        options.add(option)
        return option
    }

    // ── Execution ────────────────────────────────────────────────────────

    @TransientDsl
    internal var resolvedLocator: Locator? = null

    override fun process(locator: Locator) {
        resolvedLocator = locator
        options.forEach { it.process(locator) }
    }
}
