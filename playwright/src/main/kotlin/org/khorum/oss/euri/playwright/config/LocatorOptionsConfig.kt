package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.DefaultValue
import org.khorum.oss.konstellation.metaDsl.GeneratedDsl

@GeneratedDsl
data class LocatorOptionsConfig(
    @DefaultValue("\"\"") val hasText: String,
    @DefaultValue("\"\"") val hasNotText: String,
)
