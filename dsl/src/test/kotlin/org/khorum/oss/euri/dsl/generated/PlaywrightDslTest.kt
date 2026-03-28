package org.khorum.oss.euri.dsl.generated

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.khorum.oss.euri.dsl.models.chromium
import org.khorum.oss.euri.dsl.models.chromiumBrowser
import org.khorum.oss.euri.dsl.models.newPage
import org.khorum.oss.euri.dsl.playwright
import org.khorum.oss.geordi.UnitSim

class PlaywrightDslTest : UnitSim() {

    @Test
    @Disabled
    fun `should run playwright test with explicit`() = test<Unit> {
        given {
            val playwright = playwright {
                browser {
                    chromium()
                    context {
                        page {
                            newPage()
                        }
                        newPage {  }
                    }
                }
            }
        }
    }

    @Test
    @Disabled
    fun `should run playwright test with convenience methods`() {
        test<Unit> {
            given {
                val playwright = playwright {
                    chromiumBrowser {
                        context {
                            newPage {

                            }
                        }
                    }
                }
            }
        }
    }
}
