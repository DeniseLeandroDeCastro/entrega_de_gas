plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.entregadegas"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.entregadegas"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    //Android Core
    implementation(libs.androidx.core.ktx)

    //Android App Compat
    implementation(libs.androidx.appcompat)

    //Material
    implementation(libs.material)

    //Android Activity
    implementation(libs.androidx.activity)

    //Android Constraint Layout
    implementation(libs.androidx.constraintlayout)

    //Glide
    implementation(libs.glide)
    ksp(libs.compiler)

    //Circular Image
    implementation(libs.circleimageview)

    //Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Firebase
    implementation(libs.firebase.auth)

    //Coroutines with Firebase
    implementation(libs.kotlinx.coroutines.play.services)

    //Dagger Hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.hilt.compiler)

    //Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}