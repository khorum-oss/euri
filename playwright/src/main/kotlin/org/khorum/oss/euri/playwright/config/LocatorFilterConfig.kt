package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class LocatorFilterConfig(
    @DefaultValue("\"\"") val hasText: String,
    @DefaultValue("\"\"") val hasNotText: String,
)
