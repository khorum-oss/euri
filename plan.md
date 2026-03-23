# Playwright DSL Module - Implementation Plan

## Overview

Create a new `playwright` module that provides a Kotlin DSL for Playwright browser automation. The module is a **consumer** of the existing `@GeneratedDsl` annotation framework (no custom KSP processors). It uses **auto-managed lifecycle** (browser launch/close handled automatically). Initial scope covers **core functionality** (browser launch, context, page navigation, locators, screenshots).

## Module Structure

```
playwright/
  build.gradle.kts
  src/main/kotlin/org/khorum/oss/euri/playwright/
    PlaywrightVersion.kt
    config/
      ProxyConfig.kt
      ViewportConfig.kt
      GeolocationConfig.kt
      HttpCredentialsConfig.kt
      ScreenSizeConfig.kt
      VideoSizeConfig.kt
      ClipConfig.kt
      BrowserLaunchConfig.kt
      BrowserContextConfig.kt
      NavigationConfig.kt
      ScreenshotConfig.kt
      LocatorOptionsConfig.kt
      LocatorFilterConfig.kt
    runtime/
      PlaywrightDsl.kt          -- top-level entry, auto-managed lifecycle
      BrowserScope.kt           -- browser operations
      BrowserContextScope.kt    -- context operations
      PageScope.kt              -- page interactions
      LocatorScope.kt           -- locator chaining + actions
      FrameScope.kt             -- iframe handling
    mapping/
      ConfigMappers.kt          -- config.toPlaywright() extension functions
```

## Step-by-Step Implementation

### Step 1: Project setup
- Add `playwright` version to `gradle/libs.versions.toml`
- Add `"playwright"` to `includeModules(...)` in `settings.gradle.kts`
- Create `playwright/build.gradle.kts` matching the `dsl` module's KSP configuration but as a consumer (depends on `:dsl`, adds Playwright dependency)

### Step 2: Shared config data classes
Create `@GeneratedDsl` data classes for reusable configs:
- `ProxyConfig` (server, bypass, username, password)
- `ViewportConfig` (width, height with defaults 1280x720)
- `GeolocationConfig` (latitude, longitude, accuracy)
- `HttpCredentialsConfig` (username, password, origin, sendImmediately)
- `ScreenSizeConfig`, `VideoSizeConfig` (width, height)
- `ClipConfig` (x, y, width, height)

### Step 3: Browser launch & context config data classes
- `BrowserLaunchConfig` - headless, channel, args, proxy, slowMo, timeout, etc.
- `BrowserContextConfig` - baseURL, viewport, locale, geolocation, permissions, httpCredentials, extraHTTPHeaders, userAgent, colorScheme, etc.

### Step 4: Page operation config data classes
- `NavigationConfig` (url, waitUntil, timeout, referer)
- `ScreenshotConfig` (path, type, quality, fullPage, clip, omitBackground)
- `LocatorOptionsConfig` (hasText, hasNotText)
- `LocatorFilterConfig` (hasText, hasNotText)

### Step 5: Config-to-Playwright mappers
Create `mapping/ConfigMappers.kt` with extension functions:
- `BrowserLaunchConfig.toPlaywright() -> BrowserType.LaunchOptions`
- `BrowserContextConfig.toPlaywright() -> Browser.NewContextOptions`
- `NavigationConfig.toPlaywright() -> Page.NavigateOptions`
- `ScreenshotConfig.toPlaywright() -> Page.ScreenshotOptions`
- `ProxyConfig.toPlaywright() -> Proxy`
- `ViewportConfig.toPlaywright() -> ViewportSize` (and other shared configs)

### Step 6: Runtime scope classes
- `PlaywrightDsl.kt` - top-level entry. `playwright { chromium(); launch { headless = true }; page { ... } }`. Auto-creates Playwright instance, launches browser, creates context/page, executes actions, cleans up.
- `PageScope.kt` - wraps `Page`. navigate(), locator(), getByRole/Text/Label/TestId/Placeholder/AltText/Title(), screenshot(), content(), title(), url(), evaluate(), waitForSelector/LoadState/URL(), frame(), keyboard/mouse access, `raw` escape hatch.
- `LocatorScope.kt` - wraps `Locator`. click(), fill(), press(), check/uncheck(), hover(), focus(), clear(), selectOption(). Chaining: locator(), filter(), first/last/nth(), getBy*(). State: isVisible/Hidden/Enabled/Disabled/Checked(). Content: innerText/innerHTML/inputValue/getAttribute/textContent(). `raw` escape hatch.
- `BrowserContextScope.kt` - wraps `BrowserContext`. addCookies(), clearCookies(), grantPermissions(), setGeolocation(), setOffline(), newPage(), storageState(), `raw` escape hatch.
- `FrameScope.kt` - wraps `Frame`. Subset of PageScope: locator(), getBy*(), evaluate(), content(), url(), name().

### Step 7: Version compatibility
- `PlaywrightVersion.kt` with `TARGET_VERSION`, `MIN_VERSION`, and `checkCompatibility()` method
- Gradle dependency constraint: `strictly("[1.50.0, 1.58.0]")`

### Step 8: Verify build
- Run `./gradlew :playwright:build` to confirm KSP generation works and code compiles
- Fix any issues

### Step 9: Commit and push

## Key Design Decisions

1. **All `@EuriDsl`-annotated scope classes** prevent scope leakage (can't call `page {}` from inside `locator {}`)
2. **Every scope exposes `val raw: T`** for direct Playwright API access when the DSL doesn't cover something
3. **String enums** (e.g., `"LOAD"`, `"PNG"`) are mapped to Playwright enums in the mapper layer, keeping config classes simple
4. **Auto-managed lifecycle**: the `playwright { }` block handles `Playwright.create()`, `browser.launch()`, `browser.newContext()`, `context.newPage()`, and closes everything in reverse order via try/finally

## Target DSL Usage

```kotlin
playwright {
    chromium()
    launch { headless = true }
    context {
        viewport { width = 1920; height = 1080 }
        locale = "en-US"
    }
    page {
        navigate("https://example.com")
        getByRole(AriaRole.BUTTON, name = "Submit").click()
        screenshot { path = "result.png"; fullPage = true }
    }
}
```
