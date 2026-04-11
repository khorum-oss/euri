package org.khorum.oss.euri.dsl.config

import com.microsoft.playwright.options.Proxy
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultEmptyString

@GeneratedDsl
data class ProxyConfig(
    val server: String,
    @DefaultEmptyString val bypass: String = "",
    @DefaultEmptyString val username: String = "",
    @DefaultEmptyString val password: String = "",
) : PlaywrightConfig<Proxy> {
    override fun toPlaywright(): Proxy = Proxy(server).also {
        if (bypass.isNotEmpty()) it.setBypass(bypass)
        if (username.isNotEmpty()) it.setUsername(username)
        if (password.isNotEmpty()) it.setPassword(password)
    }
}
