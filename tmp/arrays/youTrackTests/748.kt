// Original bug: KT-42221

object Versions {
    val min_sdk = 19
    val target_sdk = 30
    val compile_sdk = 30

    val gms_plugin = "4.3.3"
    val fabric = "1.31.2"
    val kotlin = "1.4.10"
    val androidx_test = "1.2.0"
    val androidx_test_ext = "1.1.1"
    val android_gradle_plugin = "4.0.1"
    val junit = "4.13"
    val sqlDelight = "1.4.3"
    val ktor = "1.4.0"
    val multiplatformSettings = "0.6.2"
    val coroutines = "1.3.9-native-mt-2"
    val serialization = "1.0.0-RC2"
    val cocoapodsext = "0.12"
    val kermit = "0.1.8"
    val karmok = "0.1.8"
    val versions_plugin = "0.33.0"
    val klock = "1.12.1"
    val krypto = "1.12.0"
    val uuid = "0.2.2"
    val kodein = "7.1.0"
    val joda = "2.10.6"
    val room = "2.2.5"
    val dagger = "2.29.1"
    val jUnit = "4.13"
}

object Deps {
    val uuid = "com.benasher44:uuid:${Versions.uuid}"
    val gms = "com.google.gms:google-services:${Versions.gms_plugin}"
    val fabric = "io.fabric.tools:gradle:${Versions.fabric}"
    val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    val junit = "junit:junit:${Versions.junit}"
    val multiplatformSettingsNoArg = "com.russhwolf:multiplatform-settings-no-arg:${Versions.multiplatformSettings}"
    val multiplatformSettingsTest = "com.russhwolf:multiplatform-settings-test:${Versions.multiplatformSettings}"
    val cocoapodsext = "co.touchlab:kotlinnativecocoapods:${Versions.cocoapodsext}"
    val kermit = "co.touchlab:kermit:${Versions.kermit}"
    val karmok = "co.touchlab:karmok-library:${Versions.karmok}"

    object AndroidXTest {
        val core = "androidx.test:core:${Versions.androidx_test}"
        val junit = "androidx.test.ext:junit:${Versions.androidx_test_ext}"
        val runner = "androidx.test:runner:${Versions.androidx_test}"
        val rules = "androidx.test:rules:${Versions.androidx_test}"
    }

    object KotlinTest {
        val common = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
        val annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
        val jvm = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
        val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    }

    object Coroutines {
        val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object SqlDelight {
        val gradle = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
        val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        val driverIos = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
        val driverAndroid = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    }

    object Ktor {
        val commonCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        val commonJson = "io.ktor:ktor-client-json:${Versions.ktor}"
        val commonLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        val androidCore = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        val jvmJson =     "io.ktor:ktor-client-json-jvm:${Versions.ktor}"
        val jvmLogging =     "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
        val ios =         "io.ktor:ktor-client-ios:${Versions.ktor}"
        val commonSerialization ="io.ktor:ktor-client-serialization:${Versions.ktor}"
        val androidSerialization ="io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
    }

    object Korlibs {
        val klock = "com.soywiz.korlibs.klock:klock:${Versions.klock}"
        val krypto = "com.soywiz.korlibs.krypto:krypto:${Versions.krypto}"
    }

    object Serialization {
        val core = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
        val runtime = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.serialization}"
    }

    object Kotlin {
        val gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
        val androidExtensions = "org.jetbrains.kotlin:kotlin-android-extensions-runtime:${Versions.kotlin}"
        val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    }

    object Kodein {
        val android = "org.kodein.di:kodein-di-framework-android-core:${Versions.kodein}"
        val core = "org.kodein.di:kodein-di:${Versions.kodein}"
        val conf = "org.kodein.di:kodein-di-conf:${Versions.kodein}"
    }

    object Android {
        val joda = "joda-time:joda-time:${Versions.joda}"
        val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        val roomRxjava = "androidx.room:room-rxjava2:${Versions.room}"
        val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    }

    object jUnit {
        val core = "junit:junit:${Versions.junit}"
    }
}
