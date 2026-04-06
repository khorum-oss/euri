package org.khorum.oss.euri.dsl.runtime

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.BrowserType as PlaywrightBrowserType
import com.microsoft.playwright.Playwright
import org.khorum.oss.euri.dsl.config.BrowserContextConfig
import org.khorum.oss.euri.dsl.config.BrowserLaunchConfig
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.RootDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl

/**
 * Functionality:
 * - [chromium] sets the browser type to "chromium"
 * - [firefox] sets the browser type to "firefox"
 * - [webkit] sets the browser type to "webkit"
 * - [launch] sets the launch options to use
 * - [context] sets the context options to use
 * - [contextScope] sets the context actions to perform
 * - [page] sets the page actions to perform
 *
 * @property browserTypeName the browser type to use
 * @property launch the launch options to use [BrowserLaunchConfig]
 * @property context the context options to use [BrowserContextConfig]
 * @property contextActions the context actions to perform. List of [BrowserContextScope] functions
 * @property pageActions the page actions to perform. List of [PageScope] functions
 */
@GeneratedDsl("playwright")
class PlaywrightRuntime(
    private var launch: BrowserLaunchConfig? = null,
    private var context: BrowserContextConfig? = null
) {
    @TransientDsl
    private var browserTypeName: BrowserType = BrowserType.CHROMIUM

    @TransientDsl
    private var contextActions: MutableList<BrowserContextScope.() -> Unit> = mutableListOf()
//    private var pageActions: MutableList<PageScope.() -> Unit> = mutableListOf()

    fun chromium() {
        browserTypeName = BrowserType.CHROMIUM
    }

    fun firefox() {
        browserTypeName = BrowserType.FIREFOX
    }

    fun webkit() {
        browserTypeName = BrowserType.WEBKIT
    }

    fun contextScope(block: BrowserContextScope.() -> Unit) {
        contextActions.add(block)
    }
//
//    fun page(block: PageScope.() -> Unit) {
//        pageActions.add(block)
//    }
//
//    @Suppress("CyclomaticComplexMethod", "NestedBlockDepth")
//    internal fun execute() {
//        val pw = Playwright.create()
//        pw.use { pw ->
//            val browserType: PlaywrightBrowserType = when (browserTypeName) {
//                BrowserType.CHROMIUM -> pw.chromium()
//                BrowserType.FIREFOX -> pw.firefox()
//                BrowserType.WEBKIT -> pw.webkit()
//            }
//
//            val launchOpts = launch?.toPlaywright()
//            val browser: Browser = browserType.launch(launchOpts)
//            browser.use { browser ->
//                val contextOpts = context?.toPlaywright()
//                val context: BrowserContext = browser.newContext(contextOpts)
//                context.use { context ->
//                    contextActions.forEach { action ->
//                        BrowserContextScope(context).apply(action)
//                    }
//
//                    if (pageActions.isNotEmpty()) {
//                        val page = context.newPage()
//                        pageActions.forEach { action ->
//                            PageScope(page).apply(action)
//                        }
//                    }
//                }
//            }
//        }
//    }
}

/**
 * [PlaywrightRuntime]
 */
fun playwright(block: PlaywrightRuntime.() -> Unit) {
    val dsl = PlaywrightRuntime()
    dsl.apply(block)
//    dsl.execute()
}
