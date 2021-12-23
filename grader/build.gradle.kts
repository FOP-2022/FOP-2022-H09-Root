repositories {
  maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
  implementation("org.sourcegrade:jagr-grader-api:0.3-SNAPSHOT")
  implementation("fr.inria.gforge.spoon:spoon-gradle-plugin:1.4")
  implementation(project(":solution"))
}
