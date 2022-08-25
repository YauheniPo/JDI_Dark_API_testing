plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm")
}
group = "jdi.feature"
version = "unspecified"

java.sourceCompatibility = JavaVersion.VERSION_11

tasks.getByName<Jar>("jar") {
    enabled = true
}

dependencies {
    implementation(project(":components"))
    implementation("com.epam.jdi:jdi-dark:${Versions.jdiDark}")
    implementation ("com.google.protobuf:protobuf-java:${Versions.protobuf}")
    testImplementation("org.testng:testng:${Versions.testNG}")
    testImplementation("org.assertj:assertj-core:${Versions.assertj}")
}

tasks.test {
    systemProperties.putAll(System.getProperties().map { Pair(it.key.toString(), it.value) })

    useTestNG() {
        useDefaultListeners = true
    }
}
