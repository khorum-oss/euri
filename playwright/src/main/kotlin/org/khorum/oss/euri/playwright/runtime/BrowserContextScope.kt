package org.khorum.oss.euri.playwright.runtime

import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.Page
import com.microsoft.playwright.options.Cookie
import org.khorum.oss.euri.dsl.common.EuriDsl
import java.nio.file.Paths

@EuriDsl
class BrowserContextScope(private val context: BrowserContext) {

    fun addCookies(cookies: List<Cookie>) =
        context.addCookies(cookies)

    fun clearCookies() = context.clearCookies()

    fun cookies(): List<Cookie> = context.cookies()

    fun cookies(vararg urls: String): List<Cookie> = context.cookies(urls.toList())

    fun grantPermissions(permissions: List<String>) =
        context.grantPermissions(permissions)

    fun grantPermissions(permissions: List<String>, origin: String) =
        context.grantPermissions(permissions, BrowserContext.GrantPermissionsOptions().setOrigin(origin))

    fun clearPermissions() = context.clearPermissions()

    fun setGeolocation(latitude: Double, longitude: Double) =
        context.setGeolocation(com.microsoft.playwright.options.Geolocation(latitude, longitude))

    fun setOffline(offline: Boolean) = context.setOffline(offline)

    fun newPage(block: PageScope.() -> Unit) {
        val page = context.newPage()
        PageScope(page).apply(block)
    }

    fun pages(): List<Page> = context.pages()

    fun onPage(handler: (Page) -> Unit) = context.onPage(handler)

    fun storageState(): String = context.storageState()

    fun storageState(path: String): String =
        context.storageState(BrowserContext.StorageStateOptions().setPath(Paths.get(path)))

    fun close() = context.close()

    val raw: BrowserContext get() = context
}
