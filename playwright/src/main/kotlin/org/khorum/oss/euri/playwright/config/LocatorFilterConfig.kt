package org.khorum.oss.euri.playwright.config

import org.khorum.oss.konstellation.metaDsl.annotation.DefaultEmptyString
import org.khorum.oss.konstellation.metaDsl.annotation.DefaultState
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
@DefaultState
data class LocatorFilterConfig(
    @DefaultEmptyString val hasText: String = "",
    @DefaultEmptyString val hasNotText: String = "",
)
