import org.sourcegrade.submitter.submit

plugins {
    id("org.sourcegrade.submitter") version "0.4.0"
}

dependencies {
    // JUnit only available in "test" source set (./src/test)
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks {
    submit {
        assignmentId = "h09"
        studentId = "ab12cdef"
        firstName = "sol_first"
        lastName = "sol_last"
    }
    test {
        useJUnitPlatform()
    }
}
