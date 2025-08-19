plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.20")
    testImplementation("junit:junit:4.13.2")
}

kotlin {
    jvmToolchain(8)
}
