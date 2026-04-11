package org.khorum.oss.euri.dsl.config

import com.microsoft.playwright.BrowserType
import org.khorum.oss.euri.dsl.common.toPath
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.MapDsl
import org.khorum.oss.konstellation.metaDsl.annotation.RootDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultTrue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultZeroDouble
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DO_NOT
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.NOT

@RootDsl
@GeneratedDsl
data class BrowserLaunchConfig(
    @DefaultValue("30000.0") val timeout: Double = 30000.0,
    @DefaultZeroDouble val slowMo: Double = 0.0,

    @DefaultTrue(negationTemplate = DO_NOT)  val handleSighup: Boolean = true,
    @DefaultTrue(negationTemplate = DO_NOT)  val handleSigint: Boolean = true,
    @DefaultTrue(negationTemplate = DO_NOT)  val handleSigterm: Boolean = true,
    @DefaultTrue(negationTemplate = NOT)     val headless: Boolean = true,
    @DefaultFalse(negationTemplate = NOT)    val chromiumSandbox: Boolean = false,
    @DefaultFalse(negationTemplate = DO_NOT) val ignoreAllDefaultArgs: Boolean = false,

    val channel: String? = null,
    val downloadsPath: String? = null,
    val executablePath: String? = null,
    val tracesDir: String? = null,

    @ListDsl val args: List<String>? = null,
    @ListDsl val ignoreDefaultArgs: List<String>? = null,
    @MapDsl val env: Map<String, String>? = null,

    val proxy: ProxyConfig? = null
) : PlaywrightConfig<BrowserType.LaunchOptions> {
    override fun toPlaywright(): BrowserType.LaunchOptions = BrowserType.LaunchOptions().also { options ->
        options.setHeadless(headless)

        options.setChannel(channel)
        options.setArgs(args)

        options.setChromiumSandbox(chromiumSandbox)

        options.setDownloadsPath(downloadsPath?.toPath())
        options.setExecutablePath(executablePath?.toPath())

        options.setEnv(env)

        options.setHandleSIGHUP(handleSighup)
        options.setHandleSIGINT(handleSigint)
        options.setHandleSIGTERM(handleSigterm)
        options.setIgnoreAllDefaultArgs(ignoreAllDefaultArgs)

        options.setIgnoreDefaultArgs(ignoreDefaultArgs)

        proxy?.let { options.setProxy(it.toPlaywright()) }

        options.setSlowMo(slowMo)
        options.setTimeout(timeout)

        options.setTracesDir(tracesDir?.toPath())
    }
}
