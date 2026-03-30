val dslVersion: String by rootProject.extra

plugins {
    id("com.google.devtools.ksp")
}

group = "org.khorum.oss.euri"
version = dslVersion

dependencies {
    implementation(project(":dsl"))
    implementation(rootProject.libs.konstellation.meta.dsl)
    implementation(rootProject.libs.konstellation.dsl)
    implementation(rootProject.libs.ksp.api)
    implementation(rootProject.libs.kotlinpoet)
    implementation(rootProject.libs.kotlinpoet.ksp)
    implementation(rootProject.libs.google.auto.service)

    implementation(rootProject.libs.playwright)

    testImplementation(project(":core-test"))
    testImplementation(rootProject.libs.mockk)
}

tasks.jar {
    archiveBaseName.set("playwright")
}

kover {
    reports {
        filters {
            excludes {
                annotatedBy("org.khorum.oss.euri.dsl.common.ExcludeFromCoverage")
            }
        }
        verify {
            rule("playwright coverage") {
                minBound(80)
            }
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

ksp {
    arg("projectRootClasspath", "org.khorum.oss.euri")
    arg("dslBuilderClasspath", "org.khorum.oss.euri.common")
    arg("dslMarkerClass", "org.khorum.oss.euri.common.EuriDsl")
}
