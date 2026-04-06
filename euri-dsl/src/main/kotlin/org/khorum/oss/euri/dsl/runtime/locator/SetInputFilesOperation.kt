package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.KeyboardModifier
import org.khorum.oss.euri.dsl.config.PlaywrightConfig
import org.khorum.oss.euri.dsl.runtime.PlaywrightPosition
import org.khorum.oss.konstellation.metaDsl.annotation.GeneratedDsl
import org.khorum.oss.konstellation.metaDsl.annotation.ListDsl
import org.khorum.oss.konstellation.metaDsl.annotation.TransientDsl
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.DefaultValue
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.DefaultFalse
import org.khorum.oss.konstellation.metaDsl.annotation.defaults.state.standard.NegationFunctionTemplate.DO_NOT
import java.nio.file.Path

@GeneratedDsl
class SetInputFilesOperation(
    @DefaultValue("30000.0")
    val timeout: Double = 30000.0,
) : PlaywrightConfig<Locator.SetInputFilesOptions>, LocatorOperation {
    @TransientDsl
    internal var files: Array<Path>? = null

    override fun toPlaywright(): Locator.SetInputFilesOptions = Locator.SetInputFilesOptions().also { options ->
        options.setTimeout(timeout)
    }

    override fun process(locator: Locator) {
        locator.setInputFiles(files, toPlaywright())
    }
}