package org.khorum.oss.euri.dsl.models

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
class BrowserContext(
    private val page: Page? = null
)

fun BrowserContextDslBuilder.newPage(block: PageDslBuilder.() -> Unit) {
    page { 
        newPage()
        block()
    }
}