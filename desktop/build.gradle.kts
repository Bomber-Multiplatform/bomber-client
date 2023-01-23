plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.jetbrains.compose)
}

dependencies {
    implementation(projects.common)
    implementation(compose.desktop.currentOs)
    implementation(projects.features.countryFlags)
    implementation(projects.features.socialIcons)
}

compose.desktop {
    application {
        mainClass = "com.kbomber.desktop"
    }
}