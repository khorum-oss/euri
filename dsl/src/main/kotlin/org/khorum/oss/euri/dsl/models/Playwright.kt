package org.khorum.oss.euri.dsl.models

import com.microsoft.playwright.Playwright
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(isRoot = true)
data class Playwright(
    private val browser: Browser? = null,
) {
    @Suppress("UnusedPrivateProperty")
    fun run() {
        val playwright = Playwright.create()
    }
}

fun PlaywrightDslBuilder.chromiumBrowser(block: BrowserDslBuilderScope) {
    browser {
        chromium()
        block()
    }
}
