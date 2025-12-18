plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-kapt")
    id("androidx.room")
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.androidx.navigation.safeargs)
    id("app.cash.sqldelight") version "2.0.2"
}

android {
    namespace = "com.example.newsaggregator"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.newsaggregator"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.animation.core.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



    implementation ("com.google.accompanist:accompanist-swiperefresh:0.30.1")
    implementation (platform("androidx.compose:compose-bom:2023.08.00"))
    implementation ("androidx.compose.material3:material3")





    // xml
    implementation(libs.xmlutil.core)
    implementation(libs.xmlutil.serialization)
    implementation("io.github.pdvrieze.xmlutil:serialization:0.86.3")

    // retrofit
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    // coil
    implementation(libs.coil)

    // sqldelight
    implementation("app.cash.sqldelight:android-driver:2.0.2")
    implementation("app.cash.sqldelight:jdbc-driver:2.0.2")

    // room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // coroutine
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // recycler
    implementation(libs.androidx.recyclerview)

    //coil
    implementation("io.coil-kt:coil-compose:2.4.0")
}