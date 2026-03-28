package org.khorum.oss.euri.dsl.models

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
class Browser(
    private val browserType: BrowserType? = null,
    private val context: BrowserContext? = null
)

fun BrowserDslBuilder.chromium() {
    this.browserType = BrowserType.CHROMIUM
}