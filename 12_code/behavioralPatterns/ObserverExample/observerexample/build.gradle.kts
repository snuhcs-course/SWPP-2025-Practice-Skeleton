plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.20")
    testImplementation("junit:junit:4.13.2")
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("com.example.observerexample.ClientKt")
}
