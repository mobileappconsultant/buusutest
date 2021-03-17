
import Dependencies.AndroidX
import Dependencies.DI
import Dependencies.Network
import Dependencies.Performance
import Dependencies.View
import ProjectLib.cache
import ProjectLib.countrySearch
import ProjectLib.core
import ProjectLib.libCountrySearch
import ProjectLib.presentation
import ProjectLib.presentationAndroid
import ProjectLib.remote

plugins {
    androidApplication
    kotlin(kotlinAndroid)
    kotlin(kotlinKapt)
    safeArgs
    daggerHilt
}

android {
    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdkVersion(Config.Version.minSdkVersion)
        compileSdkVersion(Config.Version.compileSdkVersion)
        targetSdkVersion(Config.Version.targetSdkVersion)
        versionCode = Config.Version.versionCode
        versionName = Config.Version.versionName
        multiDexEnabled = Config.isMultiDexEnabled
        testInstrumentationRunner = Config.Android.testInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        named(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(countrySearch))
    implementation(project(cache))
    implementation(project(presentation))
    implementation(project(presentationAndroid))
    implementation(project(remote))
    implementation(project(core))
    implementation(project(libCountrySearch))
    implementation("io.coil-kt:coil:1.1.1")
    implementation("io.coil-kt:coil-svg:1.1.1")


//    debugImplementation(Performance.leakCanary)

    implementAll(View.components)
    implementation(Network.moshi)
    implementation(DI.daggerHiltAndroid)
    implementation(DI.hiltViewModel)

    AndroidX.run {
        implementation(activity)
        implementation(coreKtx)
        implementation(navigationFragmentKtx)
        implementation(navigationUiKtx)
        implementation(multiDex)
    }

    kapt(DI.AnnotationProcessor.daggerHilt)
    kapt(DI.AnnotationProcessor.jetpackHiltCompiler)
}
