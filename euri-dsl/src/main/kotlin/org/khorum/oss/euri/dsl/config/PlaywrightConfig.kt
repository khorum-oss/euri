package org.khorum.oss.euri.dsl.config

interface PlaywrightConfig<T> {
    fun toPlaywright(): T
}
