import java.util.Properties
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.excercise1"
    compileSdk = 34

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.excercise1"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val props = Properties()
        props.load(rootProject.file("local.properties").inputStream())

        buildConfigField(
            "String",
            "PIXABAY_KEY",
            "\"${props.getProperty("PIXABAY_KEY")}\""
        )
    } // ✅ QUAN TRỌNG: đóng ở đây

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
}

dependencies {

    implementation("androidx.paging:paging-runtime-ktx:3.2.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("androidx.recyclerview:recyclerview:1.3.2")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation("io.coil-kt:coil:2.5.0")
    implementation("androidx.activity:activity:1.8.2")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.cardview:cardview:1.0.0")

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}