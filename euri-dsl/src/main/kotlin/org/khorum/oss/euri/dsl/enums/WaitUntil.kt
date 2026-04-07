package org.khorum.oss.euri.dsl.enums

import com.microsoft.playwright.options.WaitUntilState

enum class WaitUntil(val playwrightValue: String) {
    Commit("COMMIT"),
    DomContentLoaded("DOMCONTENTLOADED"),
    Load("LOAD"),
    NetworkIdle("NETWORKIDLE");

    fun toPlaywright(): WaitUntilState = WaitUntilState.valueOf(playwrightValue)
}
