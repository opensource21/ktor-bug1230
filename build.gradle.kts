val ktor_version: String by project
val kotlin_version: String by project
val VERSION_NAME: String by project

plugins {
    //    application
    kotlin("jvm") version "1.3.30"
}

group = "kotlin.bugs"
version = VERSION_NAME

//application {
//    mainClassName = "io.ktor.server.netty.EngineMain"
//}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
    maven { url = uri("https://kotlin.bintray.com/kotlinx") }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-apache:$ktor_version")
    implementation("io.ktor:ktor-client-logging-jvm:$ktor_version")
    testImplementation("junit:junit:4.12")
}
