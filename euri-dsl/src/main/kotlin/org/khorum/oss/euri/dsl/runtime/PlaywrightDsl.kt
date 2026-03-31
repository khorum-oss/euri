package org.khorum.oss.euri.dsl.runtime

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Playwright
import org.khorum.oss.euri.dsl.common.EuriDsl
import org.khorum.oss.euri.dsl.config.BrowserContextConfig
import org.khorum.oss.euri.dsl.config.BrowserLaunchConfig
import org.khorum.oss.euri.dsl.mapping.toPlaywright

@EuriDsl
class PlaywrightDsl {
    private var browserTypeName: String = "chromium"
    private var launchConfig: BrowserLaunchConfig? = null
    private var contextConfig: BrowserContextConfig? = null
    private var contextActions: MutableList<BrowserContextScope.() -> Unit> = mutableListOf()
    private var pageActions: MutableList<PageScope.() -> Unit> = mutableListOf()

    fun chromium() { browserTypeName = "chromium" }
    fun firefox() { browserTypeName = "firefox" }
    fun webkit() { browserTypeName = "webkit" }

    fun launch(block: BrowserType.LaunchOptions.() -> Unit) {
        val options = BrowserType.LaunchOptions()
        block(options)
        launchOptions = options
    }

    private var launchOptions: BrowserType.LaunchOptions? = null

    fun context(block: Browser.NewContextOptions.() -> Unit) {
        val options = Browser.NewContextOptions()
        block(options)
        contextOptions = options
    }

    private var contextOptions: Browser.NewContextOptions? = null

    fun contextScope(block: BrowserContextScope.() -> Unit) {
        contextActions.add(block)
    }

    fun page(block: PageScope.() -> Unit) {
        pageActions.add(block)
    }

    internal fun execute() {
        val pw = Playwright.create()
        try {
            val browserType: BrowserType = when (browserTypeName) {
                "chromium" -> pw.chromium()
                "firefox" -> pw.firefox()
                "webkit" -> pw.webkit()
                else -> pw.chromium()
            }

            val launchOpts = launchConfig?.toPlaywright() ?: launchOptions ?: BrowserType.LaunchOptions()
            val browser: Browser = browserType.launch(launchOpts)
            try {
                val contextOpts = contextConfig?.toPlaywright() ?: contextOptions ?: Browser.NewContextOptions()
                val context: BrowserContext = browser.newContext(contextOpts)
                try {
                    contextActions.forEach { action ->
                        BrowserContextScope(context).apply(action)
                    }

                    if (pageActions.isNotEmpty()) {
                        val page = context.newPage()
                        pageActions.forEach { action ->
                            PageScope(page).apply(action)
                        }
                    }
                } finally {
                    context.close()
                }
            } finally {
                browser.close()
            }
        } finally {
            pw.close()
        }
    }
}

fun playwright(block: PlaywrightDsl.() -> Unit) {
    val dsl = PlaywrightDsl()
    dsl.apply(block)
    dsl.execute()
}
