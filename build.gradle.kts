plugins {
    java
    id("org.sourcegrade.style") version "1.2.0"
}

tasks {
    create<Jar>("graderJar") {
        group = "build"
        afterEvaluate {
            archiveFileName.set("FOP-2022-H09-${project.version}.jar")
            from(project(":grader").sourceSets.main.get().allSource)
            from(project(":solution").sourceSets.main.get().allSource)
            from(project(":solution").sourceSets.test.get().allSource)
        }
    }
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "org.sourcegrade.style")
    version = "1.0.0-SNAPSHOT"
    repositories {
        mavenCentral()
    }
    java {
        withSourcesJar()
    }
    tasks {
        jar {
            archiveFileName.set("${rootProject.name}-${project.name}.jar")
        }
        named<Jar>("sourcesJar") {
            archiveFileName.set("${rootProject.name}-${project.name}-sources.jar")
        }
    }
}
