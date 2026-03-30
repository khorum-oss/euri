package org.khorum.oss.euri.playwright.mapping

import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Browser
import com.microsoft.playwright.Page
import com.microsoft.playwright.options.Proxy
import com.microsoft.playwright.options.ScreenSize
import com.microsoft.playwright.options.ViewportSize
import com.microsoft.playwright.options.WaitUntilState
import org.khorum.oss.euri.playwright.config.*
import java.nio.file.Paths

fun ProxyConfig.toPlaywright(): Proxy {
    val proxy = Proxy(server)
    if (bypass.isNotEmpty()) proxy.setBypass(bypass)
    if (username.isNotEmpty()) proxy.setUsername(username)
    if (password.isNotEmpty()) proxy.setPassword(password)
    return proxy
}

fun ViewportConfig.toPlaywright(): ViewportSize =
    ViewportSize(width, height)

fun GeolocationConfig.toPlaywright(): com.microsoft.playwright.options.Geolocation =
    com.microsoft.playwright.options.Geolocation(latitude, longitude).setAccuracy(accuracy)

fun ScreenSizeConfig.toPlaywright(): ScreenSize =
    ScreenSize(width, height)

fun BrowserLaunchConfig.toPlaywright(): BrowserType.LaunchOptions {
    val options = BrowserType.LaunchOptions()
    options.setHeadless(headless)
    if (channel.isNotEmpty()) options.setChannel(channel)
    if (args.isNotEmpty()) options.setArgs(args)
    options.setChromiumSandbox(chromiumSandbox)
    if (downloadsPath.isNotEmpty()) options.setDownloadsPath(Paths.get(downloadsPath))
    if (executablePath.isNotEmpty()) options.setExecutablePath(Paths.get(executablePath))
    if (env.isNotEmpty()) options.setEnv(env)
    options.setHandleSIGHUP(handleSIGHUP)
    options.setHandleSIGINT(handleSIGINT)
    options.setHandleSIGTERM(handleSIGTERM)
    if (ignoreAllDefaultArgs) options.setIgnoreAllDefaultArgs(true)
    if (ignoreDefaultArgs.isNotEmpty()) options.setIgnoreDefaultArgs(ignoreDefaultArgs)
    proxy?.let { options.setProxy(it.toPlaywright()) }
    if (slowMo > 0.0) options.setSlowMo(slowMo)
    options.setTimeout(timeout)
    if (tracesDir.isNotEmpty()) options.setTracesDir(Paths.get(tracesDir))
    return options
}

fun BrowserContextConfig.toPlaywright(): Browser.NewContextOptions {
    val options = Browser.NewContextOptions()
    options.setAcceptDownloads(acceptDownloads)
    if (baseURL.isNotEmpty()) options.setBaseURL(baseURL)
    options.setBypassCSP(bypassCSP)
    if (colorScheme != "NO_PREFERENCE") {
        options.setColorScheme(
            com.microsoft.playwright.options.ColorScheme.valueOf(colorScheme)
        )
    }
    if (deviceScaleFactor != 1.0) options.setDeviceScaleFactor(deviceScaleFactor)
    if (extraHTTPHeaders.isNotEmpty()) options.setExtraHTTPHeaders(extraHTTPHeaders)
    options.setHasTouch(hasTouch)
    geolocation?.let { options.setGeolocation(it.latitude, it.longitude) }
    httpCredentials?.let { options.setHttpCredentials(it.username, it.password) }
    options.setIgnoreHTTPSErrors(ignoreHTTPSErrors)
    options.setIsMobile(isMobile)
    options.setJavaScriptEnabled(javaScriptEnabled)
    if (locale.isNotEmpty()) options.setLocale(locale)
    options.setOffline(offline)
    if (permissions.isNotEmpty()) options.setPermissions(permissions)
    proxy?.let { options.setProxy(it.server) }
    if (recordHarPath.isNotEmpty()) options.setRecordHarPath(Paths.get(recordHarPath))
    if (recordVideoDir.isNotEmpty()) options.setRecordVideoDir(Paths.get(recordVideoDir))
    recordVideoSize?.let { options.setRecordVideoSize(it.width, it.height) }
    screenSize?.let { options.setScreenSize(it.width, it.height) }
    if (storageStatePath.isNotEmpty()) options.setStorageStatePath(Paths.get(storageStatePath))
    options.setStrictSelectors(strictSelectors)
    if (timezoneId.isNotEmpty()) options.setTimezoneId(timezoneId)
    if (userAgent.isNotEmpty()) options.setUserAgent(userAgent)
    viewport?.let { options.setViewportSize(it.width, it.height) }
    return options
}

fun NavigationConfig.toNavigateOptions(): Page.NavigateOptions {
    val options = Page.NavigateOptions()
    options.setTimeout(timeout)
    options.setWaitUntil(WaitUntilState.valueOf(waitUntil))
    if (referer.isNotEmpty()) options.setReferer(referer)
    return options
}

fun ScreenshotConfig.toPlaywright(): Page.ScreenshotOptions {
    val options = Page.ScreenshotOptions()
    if (path.isNotEmpty()) options.setPath(Paths.get(path))
    options.setFullPage(fullPage)
    options.setOmitBackground(omitBackground)
    clip?.let { options.setClip(it.x, it.y, it.width, it.height) }
    if (quality >= 0) options.setQuality(quality)
    if (style.isNotEmpty()) options.setStyle(style)
    return options
}
