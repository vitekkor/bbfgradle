// Original bug: KT-42388

object Versions {
    const val gradleTools = "4.2.0-alpha12"
    const val compileSdk = 30
    const val targetSdk = compileSdk
    const val minSdk = 23
    const val buildTools = "$compileSdk.0.2"

    const val kotlin = "1.4.10"
    const val kotlinCoroutines = "1.3.9-native-mt-2"
    const val ktor = "1.4.0"
    const val kotlinxSerialization = "1.0.0-RC"
    const val koin = "3.0.0-alpha-4"
    const val core = "1.5.0-alpha02"
    const val core_ktx = core
    const val lifecycle = "2.2.0"
    const val compose = "1.0.0-alpha03"
    const val junit = "4.12"
    const val material = "1.2.1"
    const val appcompat = "1.2.0"
}


object Compose {
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val uiTooling = "androidx.ui:ui-tooling:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val runtimeLiveData =  "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
}

object Android {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val core = "androidx.core:core:${Versions.core_ktx}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
}
