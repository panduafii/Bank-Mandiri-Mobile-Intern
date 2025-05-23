plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.dagger.hilt)
    id("kotlin-parcelize")
}

// Gunakan cara Kotlin untuk membaca properties
val localProperties: Map<String, String> = (rootProject.projectDir.listFiles() ?: emptyArray())
    .find { it.name == "local.properties" }
    ?.let { file ->
        file.readLines()
            .mapNotNull { line ->
                val parts = line.split("=")
                if (parts.size == 2) parts[0].trim() to parts[1].trim() else null
            }
            .toMap()
    } ?: emptyMap()


android {
    namespace = "com.example.bankmandirimobileintern"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bankmandirimobileintern"
        minSdk = 35
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Gunakan safe call untuk mendapatkan API_KEY
        buildConfigField("String", "API_KEY", "\"${localProperties["API_KEY"] ?: ""}\"")
    }

    // Sisanya tetap sama seperti sebelumnya
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    kapt {
        correctErrorTypes = true
        useBuildCache = false
        arguments {
            arg("dagger.fastInit", "enabled")
        }
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
    implementation(libs.protolite.well.known.types)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1") // Pastikan versinya sesuai dengan Kotlin 1.9.0

    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.6.0")

    // Dagger Hilt
    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.49")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Compose Foundation
    implementation("androidx.compose.foundation:foundation:1.4.3")

    // Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.4-beta")

    // Paging 3
    implementation("androidx.paging:paging-runtime:3.1.1")
    implementation("androidx.paging:paging-compose:3.2.0-rc01")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
}
