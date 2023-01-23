plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
}

kotlin {
    jvm()
}

dependencies {
    commonMainApi(compose.ui)
    commonMainApi(compose.material)
    commonMainApi(compose.materialIconsExtended)
    commonMainApi(compose.animation)
    commonMainApi(compose.foundation)
    commonMainImplementation(compose.preview)
    commonMainApi(projects.features.countryFlags)
    commonMainApi(projects.features.socialIcons)
}