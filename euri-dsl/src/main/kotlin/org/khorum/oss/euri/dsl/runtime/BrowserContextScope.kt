package org.khorum.oss.euri.dsl.runtime

import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.Page
import com.microsoft.playwright.options.Cookie
import com.microsoft.playwright.options.Geolocation
import org.khorum.oss.euri.dsl.common.EuriDsl
import org.khorum.oss.konstellation.metaDsl.annotation.InjectDslMethod
import java.nio.file.Paths

@EuriDsl
class BrowserContextScope(private val context: BrowserContext) {

    @InjectDslMethod
    fun addCookies(cookies: List<Cookie>) =
        context.addCookies(cookies)

    @InjectDslMethod
    fun clearCookies() = context.clearCookies()

    @InjectDslMethod
    fun cookies(): List<Cookie> = context.cookies()

    @InjectDslMethod
    fun cookies(vararg urls: String): List<Cookie> = context.cookies(urls.toList())

    @InjectDslMethod
    fun grantPermissions(permissions: List<String>) =
        context.grantPermissions(permissions)

    @InjectDslMethod
    fun grantPermissions(permissions: List<String>, origin: String) =
        context.grantPermissions(permissions, BrowserContext.GrantPermissionsOptions().setOrigin(origin))

    @InjectDslMethod
    fun clearPermissions() = context.clearPermissions()

    @InjectDslMethod
    fun setGeolocation(latitude: Double, longitude: Double) =
        context.setGeolocation(Geolocation(latitude, longitude))

    @InjectDslMethod
    fun setOffline(offline: Boolean) = context.setOffline(offline)

//    @InjectDslMethod
//    fun newPage(block: PageScope.() -> Unit) {
//        val page = context.newPage()
//        PageScope(page).apply(block)
//    }

    @InjectDslMethod
    fun pages(): List<Page> = context.pages()

    @InjectDslMethod
    fun onPage(handler: (Page) -> Unit) = context.onPage(handler)

    @InjectDslMethod
    fun storageState(): String = context.storageState()

    @InjectDslMethod
    fun storageState(path: String): String =
        context.storageState(BrowserContext.StorageStateOptions().setPath(Paths.get(path)))

    @InjectDslMethod
    fun close() = context.close()

    val raw: BrowserContext get() = context
}
