package org.khorum.oss.euri.dsl.config

fun interface PlaywrightConfig<T> {
    fun toPlaywright(): T
}
