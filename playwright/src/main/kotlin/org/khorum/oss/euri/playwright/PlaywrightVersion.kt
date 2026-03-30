package org.khorum.oss.euri.playwright

object PlaywrightVersion {
    const val TARGET_VERSION = "1.50.0"
    const val MIN_VERSION = "1.44.0"

    fun checkCompatibility(): CompatibilityResult {
        val actualVersion = try {
            val clazz = Class.forName("com.microsoft.playwright.Playwright")
            clazz.`package`?.implementationVersion ?: return CompatibilityResult.Unknown
        } catch (_: ClassNotFoundException) {
            return CompatibilityResult.Missing
        }
        return when {
            actualVersion == TARGET_VERSION -> CompatibilityResult.Exact
            isVersionGreaterOrEqual(actualVersion, MIN_VERSION) -> CompatibilityResult.Compatible(actualVersion)
            else -> CompatibilityResult.Incompatible(actualVersion)
        }
    }

    private fun isVersionGreaterOrEqual(actual: String, minimum: String): Boolean {
        val actualParts = actual.split(".").mapNotNull { it.toIntOrNull() }
        val minimumParts = minimum.split(".").mapNotNull { it.toIntOrNull() }
        for (i in 0 until maxOf(actualParts.size, minimumParts.size)) {
            val a = actualParts.getOrElse(i) { 0 }
            val m = minimumParts.getOrElse(i) { 0 }
            if (a > m) return true
            if (a < m) return false
        }
        return true
    }
}

sealed class CompatibilityResult {
    data object Exact : CompatibilityResult()
    data class Compatible(val actualVersion: String) : CompatibilityResult()
    data class Incompatible(val actualVersion: String) : CompatibilityResult()
    data object Missing : CompatibilityResult()
    data object Unknown : CompatibilityResult()
}
