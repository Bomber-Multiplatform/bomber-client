plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.android.library)
}

kotlin {
    jvm()
    android()
}

android {
    compileSdk = 33

    sourceSets {
        getByName("main") {
            manifest {
                srcFile("src/androidMain/AndroidManifest.xml")
            }
        }
    }
}

dependencies {
    commonMainImplementation(compose.ui)
    commonMainImplementation(compose.foundation)
}