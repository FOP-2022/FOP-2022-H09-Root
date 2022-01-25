dependencies {
    compileOnly("org.sourcegrade:jagr-launcher:0.3.0")
    compileOnly("org.sourcegrade:jagr-grader-api:0.3")
    implementation("org.sourcegrade:docwatcher-api:0.1")

    implementation("org.mockito:mockito-core:4.2.0")
    implementation("fr.inria.gforge.spoon:spoon-core:10.0.0")
    implementation(project(":solution"))
}

tasks {
    create<Jar>("buildLibs") {
        group = "build"
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        val runtimeDeps = sourceSets.main.get().runtimeClasspath.mapNotNull {
            if (it.path.toLowerCase().contains("h09")) {
                null
            } else if (it.isDirectory) {
                it
            } else {
                zipTree(it)
            }
        }
        from(runtimeDeps)
        archiveFileName.set("h09-libs.jar")
    }
}
