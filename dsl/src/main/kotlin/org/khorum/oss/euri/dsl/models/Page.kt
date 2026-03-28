package org.khorum.oss.euri.dsl.models

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
class Page(
    val type: Type? = null,
    private val navigationUrl: String? = null,
) {
    enum class Type {
        NEW
    }
}

fun PageDslBuilder.newPage() {
    this.type = Page.Type.NEW
}
