import kr.entree.spigradle.data.Load
import kr.entree.spigradle.kotlin.protocolLib
import kr.entree.spigradle.kotlin.spigot

plugins {
    java
    id("kr.entree.spigradle") version "2.0.1"
    id("io.freefair.lombok") version "5.1.0"
}

group = "net.jasonhk.minecraft.plugins"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    protocolLib()

    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
}

dependencies {
    compileOnly(spigot("1.12.2-R0.1-SNAPSHOT"))
    compileOnly(protocolLib("4.5.1"))

    testImplementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

spigot {
    description = "Provides eating support in Los Sengas."
    authors = listOf("Jason Kwok")
    load = Load.POST_WORLD
    depends = listOf("ProtocolLib")

    debug {
        buildVersion = "1.12.2"
        eula = true
    }
}
