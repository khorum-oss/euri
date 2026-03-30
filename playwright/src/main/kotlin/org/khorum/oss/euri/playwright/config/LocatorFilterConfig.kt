package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyString

@GeneratedDsl
data class LocatorFilterConfig(
    @DefaultEmptyString val hasText: String = "",
    @DefaultEmptyString val hasNotText: String = "",
)
