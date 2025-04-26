plugins {
    kotlin("jvm") version "2.1.20"
    id("org.jetbrains.intellij.platform") version "2.5.0"
    id("org.jetbrains.compose") version "1.7.3"
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.20"
}

repositories {
    intellijPlatform {
        defaultRepositories()
    }
    mavenCentral()
    google() // jetpack compose
    maven("https://packages.jetbrains.team/maven/p/kpm/public/") // jewel
}

dependencies {
    intellijPlatform {
        intellijIdeaCommunity("2025.1")
    }
    
    implementation("org.jetbrains.jewel:jewel-ide-laf-bridge-243:0.27.0")
    api(compose.desktop.currentOs) {
        exclude(group = "org.jetbrains.compose.material")
        exclude(group = "org.jetbrains.kotlinx")
    }
}
