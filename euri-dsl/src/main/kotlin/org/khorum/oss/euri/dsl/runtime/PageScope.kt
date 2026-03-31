package org.khorum.oss.euri.dsl.runtime

import com.microsoft.playwright.*
import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.options.LoadState
import org.khorum.oss.euri.dsl.common.EuriDsl

@EuriDsl
class PageScope(private val page: Page) {

    // Navigation
    fun navigate(url: String): Response? =
        page.navigate(url)

    fun navigate(url: String, block: Page.NavigateOptions.() -> Unit): Response? {
        val options = Page.NavigateOptions()
        block(options)
        return page.navigate(url, options)
    }

    fun reload(block: (Page.ReloadOptions.() -> Unit)? = null): Response? {
        val options = Page.ReloadOptions()
        block?.invoke(options)
        return page.reload(options)
    }

    fun goBack(block: (Page.GoBackOptions.() -> Unit)? = null): Response? {
        val options = Page.GoBackOptions()
        block?.invoke(options)
        return page.goBack(options)
    }

    fun goForward(block: (Page.GoForwardOptions.() -> Unit)? = null): Response? {
        val options = Page.GoForwardOptions()
        block?.invoke(options)
        return page.goForward(options)
    }

    // Content
    fun setContent(html: String, block: (Page.SetContentOptions.() -> Unit)? = null) {
        val options = Page.SetContentOptions()
        block?.invoke(options)
        page.setContent(html, options)
    }

    fun content(): String = page.content()

    fun title(): String = page.title()

    fun url(): String = page.url()

    // Locators
    fun locator(selector: String): LocatorScope =
        LocatorScope(page.locator(selector))

    fun locator(selector: String, block: Page.LocatorOptions.() -> Unit): LocatorScope {
        val options = Page.LocatorOptions()
        block(options)
        return LocatorScope(page.locator(selector, options))
    }

    fun getByRole(role: AriaRole, block: (Page.GetByRoleOptions.() -> Unit)? = null): LocatorScope {
        val options = Page.GetByRoleOptions()
        block?.invoke(options)
        return LocatorScope(page.getByRole(role, options))
    }

    fun getByText(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(page.getByText(text, Page.GetByTextOptions().setExact(exact)))

    fun getByLabel(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(page.getByLabel(text, Page.GetByLabelOptions().setExact(exact)))

    fun getByPlaceholder(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(page.getByPlaceholder(text, Page.GetByPlaceholderOptions().setExact(exact)))

    fun getByTestId(testId: String): LocatorScope =
        LocatorScope(page.getByTestId(testId))

    fun getByAltText(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(page.getByAltText(text, Page.GetByAltTextOptions().setExact(exact)))

    fun getByTitle(text: String, exact: Boolean = false): LocatorScope =
        LocatorScope(page.getByTitle(text, Page.GetByTitleOptions().setExact(exact)))

    // Screenshots
    fun screenshot(block: Page.ScreenshotOptions.() -> Unit): ByteArray {
        val options = Page.ScreenshotOptions()
        block(options)
        return page.screenshot(options)
    }

    // PDF (Chromium only)
    fun pdf(block: Page.PdfOptions.() -> Unit): ByteArray {
        val options = Page.PdfOptions()
        block(options)
        return page.pdf(options)
    }

    // Evaluation
    fun evaluate(expression: String): Any? = page.evaluate(expression)

    fun evaluate(expression: String, arg: Any?): Any? = page.evaluate(expression, arg)

    // Network interception
    fun route(urlPattern: String, handler: (Route) -> Unit) =
        page.route(urlPattern, handler)

    fun unroute(urlPattern: String) = page.unroute(urlPattern)

    // Events
    fun onRequest(handler: (Request) -> Unit) = page.onRequest(handler)

    fun onResponse(handler: (Response) -> Unit) = page.onResponse(handler)

    fun onDialog(handler: (Dialog) -> Unit) = page.onDialog(handler)

    fun onConsoleMessage(handler: (ConsoleMessage) -> Unit) = page.onConsoleMessage(handler)

    // Waiting
    fun waitForSelector(selector: String, block: (Page.WaitForSelectorOptions.() -> Unit)? = null): ElementHandle? {
        val options = Page.WaitForSelectorOptions()
        block?.invoke(options)
        return page.waitForSelector(selector, options)
    }

    fun waitForLoadState(state: LoadState? = null) {
        if (state != null) page.waitForLoadState(state) else page.waitForLoadState()
    }

    fun waitForURL(url: String, block: (Page.WaitForURLOptions.() -> Unit)? = null) {
        val options = Page.WaitForURLOptions()
        block?.invoke(options)
        page.waitForURL(url, options)
    }

    fun waitForTimeout(timeout: Double) = page.waitForTimeout(timeout)

    // Frames
    fun frame(name: String): FrameScope? =
        page.frame(name)?.let { FrameScope(it) }

    fun frameLocator(selector: String): FrameLocator =
        page.frameLocator(selector)

    // Drag & Drop
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
    fun close(block: (Page.CloseOptions.() -> Unit)? = null) {
        val options = Page.CloseOptions()
        block?.invoke(options)
        page.close(options)
    }

    val raw: Page get() = page
}
