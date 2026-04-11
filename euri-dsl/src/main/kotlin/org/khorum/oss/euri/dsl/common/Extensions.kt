package org.khorum.oss.euri.dsl.common

import java.nio.file.Path
import java.nio.file.Paths

fun String.toPath(): Path = Paths.get(this)
