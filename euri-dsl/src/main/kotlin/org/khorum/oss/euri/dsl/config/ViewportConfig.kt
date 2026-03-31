package org.khorum.oss.euri.dsl.config

import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue

@GeneratedDsl
data class ViewportConfig(
    @DefaultValue("1280") val width: Int = 1280,
    @DefaultValue("720") val height: Int = 720,
)
