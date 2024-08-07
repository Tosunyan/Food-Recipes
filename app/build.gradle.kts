plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("kotlin-android")
    id("kotlin-parcelize")

    id("com.google.devtools.ksp") version "1.9.24-1.0.20"

    kotlin("plugin.serialization") version "1.9.22"
}

android {

    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pizzaHut"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "0.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    namespace = "com.example.foodRecipes"
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.5.1")

    // Kotlin coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    // Material Design
    implementation("com.google.android.material:material:1.6.1")

    // Fragment KTX
    implementation("androidx.fragment:fragment-ktx:1.5.2")

    // Navigation
    implementation(project(":navigation"))

    // Coil
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    // KotlinX Serialization
    val kotlinX = "1.6.3"
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinX")

    // Retrofit
    val retrofitVersion = "2.11.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-kotlinx-serialization:$retrofitVersion")

    // Jetpack Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.06.00")
    implementation(composeBom)
    implementation("androidx.compose.foundation:foundation:1.7.0-beta05")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.3")
    implementation("com.github.inconcept:android-design-system:1.0.0")

    debugImplementation("androidx.compose.ui:ui-tooling")
}