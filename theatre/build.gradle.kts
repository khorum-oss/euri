
plugins {
    application
}

dependencies {
    implementation(project(":euri-dsl"))
}

application {
    mainClass.set("org.khorum.oss.euri.theatre.TheatreKt")
}