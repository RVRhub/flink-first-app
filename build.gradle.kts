plugins {
    kotlin("jvm") version "1.9.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "dev.rvr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.flink:flink-java:1.14.0")
    implementation("org.apache.flink:flink-streaming-java_2.11:1.14.0")
    implementation("org.apache.flink:flink-clients_2.11:1.14.0")
    implementation("org.apache.flink:flink-connector-elasticsearch7_2.11:1.14.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks {
    shadowJar {
        archiveBaseName.set("flink-app")
        archiveVersion.set("0.0.1")
        archiveClassifier.set("")
        manifest {
            attributes["Main-Class"] = "dev.rvr.StreamingWithSnapshot"
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}