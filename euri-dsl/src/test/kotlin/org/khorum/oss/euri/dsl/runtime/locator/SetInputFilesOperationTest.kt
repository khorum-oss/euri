package org.khorum.oss.euri.dsl.runtime.locator

import com.microsoft.playwright.Locator
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import java.nio.file.Path

class SetInputFilesOperationTest {

    @Test
    fun `process calls locator setInputFiles with paths`() {
        val locator = mockk<Locator>(relaxed = true)
        val paths = arrayOf(Path.of("/tmp/file1.txt"), Path.of("/tmp/file2.txt"))

        val op = SetInputFilesOperation()
        op.files = paths
        op.process(locator)

        verify { locator.setInputFiles(paths, any<Locator.SetInputFilesOptions>()) }
    }

    @Test
    fun `toPlaywright sets timeout`() {
        val op = SetInputFilesOperation(timeout = 5000.0)
        val options = op.toPlaywright()
        assertNotNull(options)
    }

    @Test
    fun `default timeout is 30000`() {
        val op = SetInputFilesOperation()
        kotlin.test.assertEquals(30000.0, op.timeout)
    }
}
