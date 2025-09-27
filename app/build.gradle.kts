plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt") // REQUIRED for Hilt code generation
    id("dagger.hilt.android.plugin") // REQUIRED for Hilt to work
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.interna"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.interna"
        minSdk = 24
        targetSdk = 36
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
    implementation(libs.androidx.navigation.compose)
    implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.foundation)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.accompanist.navigation.animation)

    implementation(libs.lottie.compose)

    implementation(libs.androidx.foundation.v160)

    implementation(libs.androidx.animation)

    implementation(libs.androidx.foundation.vversion)

    implementation (libs.accompanist.flowlayout)

    implementation (libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.firebase.firestore.ktx.v24103)

    implementation (libs.androidx.datastore.preferences)

    implementation("com.github.skydoves:balloon-compose:1.6.13")

    implementation("com.github.binayshaw7777:KotStep:3.0.1")

    implementation(libs.coil.compose) // preload images before navigation to save memory (improves performance from loading images)

    implementation(libs.hilt.android)                       //implementation("com.google.dagger:hilt-android:2.51")
    kapt(libs.hilt.compiler)                                //kapt("com.google.dagger:hilt-compiler:2.51")
    implementation(libs.androidx.hilt.navigation.compose)   //implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Room Database
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    implementation("com.github.yalantis:ucrop:2.2.8") // image cropping after picking image from user's album

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:34.3.0"))
    // Add the dependency for Cloud Storage (KTX is now included in the main module)
    implementation(libs.firebase.storage)
}