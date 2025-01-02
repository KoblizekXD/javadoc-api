plugins {
    id("java")
    id("com.gradleup.shadow") version "9.0.0-beta4"
}

group = "lol.koblizek"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("io.javalin:javalin:6.4.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.18.2")
    implementation("com.github.javaparser:javaparser-core:3.26.3")
}

tasks.build.get().dependsOn(tasks.shadowJar)

tasks.test {
    useJUnitPlatform()
}