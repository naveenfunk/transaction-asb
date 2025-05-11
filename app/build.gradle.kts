plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "nz.co.test.transactions"
    compileSdk = 35

    defaultConfig {
        applicationId = "nz.co.test.transactions"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }
}

dependencies {

    implementation(libs.dagger.core)
    ksp(libs.dagger.compiler)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    ksp(libs.dagger.android.processor)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.moshi)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso)

}