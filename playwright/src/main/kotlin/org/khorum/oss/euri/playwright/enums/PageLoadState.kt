package org.khorum.oss.euri.playwright.enums

import com.microsoft.playwright.options.LoadState

enum class PageLoadState(val playwrightValue: String) {
    DomContentLoaded("DOMCONTENTLOADED"),
    Load("LOAD"),
    NetworkIdle("NETWORKIDLE");

    fun toPlaywright(): LoadState = LoadState.valueOf(playwrightValue)
}
