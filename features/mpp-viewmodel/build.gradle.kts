plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
}

kotlin {
    android()
    jvm()

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.viewmodel)
            }
        }
    }
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
    commonMainApi(libs.kotlinx.coroutines)
}