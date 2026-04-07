package org.khorum.oss.euri.playwright.runtime

import com.microsoft.playwright.*
import org.khorum.oss.euri.dsl.common.EuriDsl
import org.khorum.oss.euri.playwright.enums.PageLoadState
import org.khorum.oss.euri.playwright.enums.Role
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
    fun locator(selector: String): LocatorScope =
        LocatorScope(page.locator(selector))

    @InjectDslMethod
    fun locator(selector: String, block: Page.LocatorOptions.() -> Unit): LocatorScope {
        val options = Page.LocatorOptions()
        block(options)
        return LocatorScope(page.locator(selector, options))
    }

    @InjectDslMethod
    fun getByRole(role: Role, block: (Page.GetByRoleOptions.() -> Unit)? = null): LocatorScope {
        val options = Page.GetByRoleOptions()
        block?.invoke(options)
        return LocatorScope(page.getByRole(role.toPlaywright(), options))
    }

    @InjectDslMethod
    fun getByText(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(page.getByText(text, Page.GetByTextOptions().setExact(exact)))

    @InjectDslMethod
    fun getByLabel(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(page.getByLabel(text, Page.GetByLabelOptions().setExact(exact)))

    @InjectDslMethod
    fun getByPlaceholder(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(page.getByPlaceholder(text, Page.GetByPlaceholderOptions().setExact(exact)))

    @InjectDslMethod
    fun getByTestId(testId: String): LocatorScope =
        LocatorScope(page.getByTestId(testId))

    @InjectDslMethod
    fun getByAltText(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(page.getByAltText(text, Page.GetByAltTextOptions().setExact(exact)))

    @InjectDslMethod
    fun getByTitle(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(page.getByTitle(text, Page.GetByTitleOptions().setExact(exact)))

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
    fun waitForLoadState(state: PageLoadState? = null) {
        if (state != null) page.waitForLoadState(state.toPlaywright()) else page.waitForLoadState()
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
    fun frame(name: String): FrameScope? =
        page.frame(name)?.let { FrameScope(it) }

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
