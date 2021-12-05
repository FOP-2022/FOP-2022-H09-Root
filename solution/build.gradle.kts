dependencies {
  // JUnit only available in "test" source set (./src/test)
  testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks {
  test {
    useJUnitPlatform()
  }
}
