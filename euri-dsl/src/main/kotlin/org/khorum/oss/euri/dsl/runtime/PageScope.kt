package org.khorum.oss.euri.dsl.runtime

import com.microsoft.playwright.ConsoleMessage
import com.microsoft.playwright.Dialog
import com.microsoft.playwright.ElementHandle
import com.microsoft.playwright.Frame
import com.microsoft.playwright.FrameLocator
import com.microsoft.playwright.Keyboard
import com.microsoft.playwright.Mouse
import com.microsoft.playwright.Page
import com.microsoft.playwright.Request
import com.microsoft.playwright.Response
import com.microsoft.playwright.Route
import com.microsoft.playwright.Touchscreen
import com.microsoft.playwright.options.LoadState
import org.khorum.oss.euri.dsl.common.EuriDsl
import org.khorum.oss.euri.dsl.enums.Role
import org.khorum.oss.euri.dsl.runtime.locator.LocatorOperation
import org.khorum.oss.konstellation.metaDsl.annotation.InjectDslMethod

@EuriDsl
class PageScope(private val page: Page) {

    // Navigation
    @InjectDslMethod
    fun navigate(url: String): Response? =
        page.navigate(url)

    @InjectDslMethod
    fun navigate(url: String, block: Page.NavigateOptions.() -> Unit): Response? {
        val options = Page.NavigateOptions()
        block(options)
        return page.navigate(url, options)
    }

    @InjectDslMethod
    fun reload(block: (Page.ReloadOptions.() -> Unit)? = null): Response? {
        val options = Page.ReloadOptions()
        block?.invoke(options)
        return page.reload(options)
    }

    @InjectDslMethod
    fun goBack(block: (Page.GoBackOptions.() -> Unit)? = null): Response? {
        val options = Page.GoBackOptions()
        block?.invoke(options)
        return page.goBack(options)
    }

    @InjectDslMethod
    fun goForward(block: (Page.GoForwardOptions.() -> Unit)? = null): Response? {
        val options = Page.GoForwardOptions()
        block?.invoke(options)
        return page.goForward(options)
    }

    // Content
    @InjectDslMethod
    fun setContent(html: String, block: (Page.SetContentOptions.() -> Unit)? = null) {
        val options = Page.SetContentOptions()
        block?.invoke(options)
        page.setContent(html, options)
    }

    @InjectDslMethod
    fun content(): String = page.content()

    @InjectDslMethod
    fun title(): String = page.title()

    @InjectDslMethod
    fun url(): String = page.url()

    // Locators
    @InjectDslMethod
    fun locator(selector: String, block: LocatorScope.() -> Unit) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(page.locator(selector))
    }

    @InjectDslMethod
    fun locator(selector: String, options: Page.LocatorOptions, block: LocatorScope.() -> Unit) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(page.locator(selector, options))
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
        val roleOptions = Page.GetByRoleOptions()
        checked?.let { roleOptions.setChecked(it) }
        disabled?.let { roleOptions.setDisabled(it) }
        exact?.let { roleOptions.setExact(it) }
        expanded?.let { roleOptions.setExpanded(it) }
        includeHidden?.let { roleOptions.setIncludeHidden(it) }
        level?.let { roleOptions.setLevel(it) }
        name?.let { roleOptions.setName(it) }
        pressed?.let { roleOptions.setPressed(it) }
        selected?.let { roleOptions.setSelected(it) }
        scope.process(page.getByRole(role.toPlaywright(), roleOptions))
    }

    @InjectDslMethod
    fun getByText(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(page.getByText(text, Page.GetByTextOptions().setExact(exact)))
    }

    @InjectDslMethod
    fun getByLabel(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(page.getByLabel(text, Page.GetByLabelOptions().setExact(exact)))
    }

    @InjectDslMethod
    fun getByPlaceholder(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(page.getByPlaceholder(text, Page.GetByPlaceholderOptions().setExact(exact)))
    }

    @InjectDslMethod
    fun getByTestId(testId: String, block: LocatorScope.() -> Unit = {}) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(page.getByTestId(testId))
    }

    @InjectDslMethod
    fun getByAltText(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(page.getByAltText(text, Page.GetByAltTextOptions().setExact(exact)))
    }

    @InjectDslMethod
    fun getByTitle(text: String, exact: Boolean = false, block: LocatorScope.() -> Unit = {}) {
        val ops = mutableListOf<LocatorOperation>()
        val scope = LocatorScope(ops)
        scope.apply(block)
        scope.process(page.getByTitle(text, Page.GetByTitleOptions().setExact(exact)))
    }

    // Screenshots
    @InjectDslMethod
    fun screenshot(block: Page.ScreenshotOptions.() -> Unit): ByteArray {
        val options = Page.ScreenshotOptions()
        block(options)
        return page.screenshot(options)
    }

    // PDF (Chromium only)
    @InjectDslMethod
    fun pdf(block: Page.PdfOptions.() -> Unit): ByteArray {
        val options = Page.PdfOptions()
        block(options)
        return page.pdf(options)
    }

    // Evaluation
    @InjectDslMethod
    fun evaluate(expression: String): Any? = page.evaluate(expression)

    @InjectDslMethod
    fun evaluate(expression: String, arg: Any?): Any? = page.evaluate(expression, arg)

    // Network interception
    @InjectDslMethod
    fun route(urlPattern: String, handler: (Route) -> Unit) =
        page.route(urlPattern, handler)

    @InjectDslMethod
    fun unroute(urlPattern: String) = page.unroute(urlPattern)

    // Events
    @InjectDslMethod
    fun onRequest(handler: (Request) -> Unit) = page.onRequest(handler)

    @InjectDslMethod
    fun onResponse(handler: (Response) -> Unit) = page.onResponse(handler)

    @InjectDslMethod
    fun onDialog(handler: (Dialog) -> Unit) = page.onDialog(handler)

    @InjectDslMethod
    fun onConsoleMessage(handler: (ConsoleMessage) -> Unit) = page.onConsoleMessage(handler)

    // Waiting
    @InjectDslMethod
    fun waitForSelector(selector: String, block: (Page.WaitForSelectorOptions.() -> Unit)? = null): ElementHandle? {
        val options = Page.WaitForSelectorOptions()
        block?.invoke(options)
        return page.waitForSelector(selector, options)
    }

    @InjectDslMethod
    fun waitForLoadState(state: LoadState? = null) {
        if (state != null) page.waitForLoadState(state) else page.waitForLoadState()
    }

    @InjectDslMethod
    fun waitForURL(url: String, block: (Page.WaitForURLOptions.() -> Unit)? = null) {
        val options = Page.WaitForURLOptions()
        block?.invoke(options)
        page.waitForURL(url, options)
    }

    @InjectDslMethod
    fun waitForTimeout(timeout: Double) = page.waitForTimeout(timeout)

    // Frames
    @InjectDslMethod
    fun frame(name: String, block: FrameScope.() -> Unit) {
        page.frame(name)?.let { FrameScope(it).apply(block) }
    }

    @InjectDslMethod
    fun frameLocator(selector: String): FrameLocator =
        page.frameLocator(selector)

    // Drag & Drop
    @InjectDslMethod
    fun dragAndDrop(source: String, target: String, block: (Page.DragAndDropOptions.() -> Unit)? = null) {
        val options = Page.DragAndDropOptions()
        block?.invoke(options)
        page.dragAndDrop(source, target, options)
    }

    // Keyboard & Mouse
    val keyboard: Keyboard get() = page.keyboard()
    val mouse: Mouse get() = page.mouse()
    val touchscreen: Touchscreen get() = page.touchscreen()

    // Close
    @InjectDslMethod
    fun close(block: (Page.CloseOptions.() -> Unit)? = null) {
        val options = Page.CloseOptions()
        block?.invoke(options)
        page.close(options)
    }

    val raw: Page get() = page
}
