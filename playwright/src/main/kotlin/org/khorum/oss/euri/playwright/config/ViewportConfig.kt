package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class ViewportConfig(
    @DefaultValue("1280") val width: Int,
    @DefaultValue("720") val height: Int,
)
