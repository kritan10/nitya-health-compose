plugins {
    id("com.google.devtools.ksp")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlinx-serialization")
}

android {
    namespace = "com.kritan.nityahealth"
    compileSdk = 34

    hilt {
        enableAggregatingTask = true
    }

    defaultConfig {
        applicationId = "com.kritan.nityahealth"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_17
        sourceCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures {
        viewBinding = true
    }

}


dependencies {
    val nav_version = "2.7.3"
    val accompanist_version = "0.30.1"
    val retrofit_version = "2.9.0"
    val hilt_version = "2.47"
    val coil_version = "2.4.0"
    val room_version = "2.5.2"
    val camerax_version = "1.2.3"
    val ktor_version = "2.3.4"

    //ksp
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.0-1.0.11")

    //Kotlin extensions
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    //Compose
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.1.2")

    //Icons-Extended
    implementation("androidx.compose.material:material-icons-extended")

    //Splash-Screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    //Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanist_version")

    //Navigation
    implementation("androidx.navigation:navigation-compose:$nav_version")

    //Retrofit
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")

    //Hilt & Dagger
    implementation("com.google.dagger:hilt-android:$hilt_version")
    ksp("com.google.dagger:hilt-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Coil
    implementation("io.coil-kt:coil-compose:$coil_version")
    implementation("io.coil-kt:coil-gif:$coil_version")

    //Room
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    //DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //CameraX
    implementation("androidx.camera:camera-core:$camerax_version")
    implementation("androidx.camera:camera-camera2:$camerax_version")
    implementation("androidx.camera:camera-lifecycle:$camerax_version")
    implementation("androidx.camera:camera-video:$camerax_version")
    implementation("androidx.camera:camera-view:$camerax_version")
    implementation("androidx.camera:camera-extensions:$camerax_version")
    implementation("com.google.accompanist:accompanist-permissions:$accompanist_version")

    //Image Cropper
    implementation("com.vanniktech:android-image-cropper:4.5.0")

    //Facebook Login SDK
    implementation("com.facebook.android:facebook-login:16.2.0")

    //Google Login
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    //Google Places SDK
    //noinspection GradleDependency
    implementation("com.google.android.libraries.places:places:3.1.0")

    //ktor
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-okhttp:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")


    //Test&Tooling
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}