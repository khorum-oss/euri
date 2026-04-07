package org.khorum.oss.euri.playwright.runtime

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Playwright
import org.khorum.oss.euri.dsl.common.EuriDsl
import org.khorum.oss.euri.playwright.config.BrowserContextConfig
import org.khorum.oss.euri.playwright.config.BrowserLaunchConfig
import org.khorum.oss.euri.playwright.mapping.toPlaywright
import org.khorum.oss.konstellation.metaDsl.annotation.InjectDslMethod

@EuriDsl
class PlaywrightDsl {
    private var browserTypeName: String = "chromium"
    private var launchConfig: BrowserLaunchConfig? = null
    private var contextConfig: BrowserContextConfig? = null
    private var contextActions: MutableList<BrowserContextScope.() -> Unit> = mutableListOf()
    private var pageActions: MutableList<PageScope.() -> Unit> = mutableListOf()

    @InjectDslMethod
    fun chromium() { browserTypeName = "chromium" }

    @InjectDslMethod
    fun firefox() { browserTypeName = "firefox" }

    @InjectDslMethod
    fun webkit() { browserTypeName = "webkit" }

    @InjectDslMethod
    fun launch(block: BrowserType.LaunchOptions.() -> Unit) {
        val options = BrowserType.LaunchOptions()
        block(options)
        launchOptions = options
    }

    private var launchOptions: BrowserType.LaunchOptions? = null

    @InjectDslMethod
    fun context(block: Browser.NewContextOptions.() -> Unit) {
        val options = Browser.NewContextOptions()
        block(options)
        contextOptions = options
    }

    private var contextOptions: Browser.NewContextOptions? = null

    @InjectDslMethod
    fun contextScope(block: BrowserContextScope.() -> Unit) {
        contextActions.add(block)
    }

    @InjectDslMethod
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
