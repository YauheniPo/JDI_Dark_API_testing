import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm")
    id ("org.springframework.boot") version Versions.springBoot
    id ("io.spring.dependency-management") version Versions.springDepsManagerPlugin
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}")
    runtimeOnly("ch.qos.logback:logback-core:${Versions.logback}")
    runtimeOnly("ch.qos.logback:logback-classic:${Versions.logback}")
    runtimeOnly("ch.qos.logback:logback-access:${Versions.logback}")
    implementation("org.amshove.kluent:kluent:${Versions.kluent}")
    implementation("com.checkout:checkout-sdk-java:${Versions.checkout}")
    testImplementation("org.assertj:assertj-core:${Versions.assertj}")
}
