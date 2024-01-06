plugins {
    kotlin("jvm") version "1.9.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "dev.rvr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

var flinkVersion = "1.17.2"

dependencies {
    implementation("org.apache.flink:flink-java:$flinkVersion")
    implementation("org.apache.flink:flink-streaming-java:$flinkVersion")
    implementation("org.apache.flink:flink-clients:$flinkVersion")
    implementation("org.apache.flink:flink-connector-kafka:$flinkVersion")
    implementation("org.apache.flink:flink-connector-elasticsearch7:1.16.3")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks {
    shadowJar {
        archiveBaseName.set("flink-app")
        archiveVersion.set("0.0.1")
        archiveClassifier.set("")
        manifest {
            attributes["Main-Class"] = "dev.rvr.KafkaStreaming"
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}