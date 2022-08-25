buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath ("com.google.protobuf:protobuf-gradle-plugin:${Versions.protobufPlugin}")
    }
}

plugins {
    kotlin("jvm") version Versions.kotlin
    id ("java")
}

group = "jdi.feature"
java.sourceCompatibility = JavaVersion.VERSION_11

allprojects {
    configurations.all {
        exclude (module = "slf4j-log4j12")
    }

    repositories{
        mavenLocal()
    }
}