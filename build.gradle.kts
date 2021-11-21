plugins {
    kotlin("jvm") version "1.5.10"
    kotlin("kapt") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
    id("java")
    `maven-publish`
    `java-library`
}

group = "com.github.miong"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")

}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
