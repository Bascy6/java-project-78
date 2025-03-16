plugins {
    id("java")
    id("checkstyle")
    jacoco
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    implementation("com.puppycrawl.tools:checkstyle:10.17.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}