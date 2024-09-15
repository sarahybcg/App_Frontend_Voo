plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.voo.bustracker.voo_app_frontend"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.voo.bustracker.voo_app_frontend"
        minSdk = 22
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Otras dependencias
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.5")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
//    implementation("androidx.wear.compose:compose-material3:1.0.0-alpha24")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // SPLASH SCREEN
    implementation("androidx.core:core-splashscreen:1.0.1")

    // MATERIAL 3
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.compose.material3:material3:1.3.0")

    // ICONS
    implementation("androidx.compose.material:material-icons-core:1.7.1")
    implementation("androidx.compose.material:material-icons-extended:1.7.1")

    // LIFECYCLE RUNTIME
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.5")
    implementation("androidx.compose.runtime:runtime-livedata:1.7.0")// Asegúrate de usar la versión correcta de Compose

    // COIL (para imágenes)
    implementation("io.coil-kt:coil-compose:2.2.2")

    // UI
    implementation("androidx.compose.ui:ui:1.7.1")
    implementation("androidx.compose.foundation:foundation:1.7.1")

    // NAVIGATION
    implementation("androidx.navigation:navigation-compose:2.8.0")

    // Otros componentes de UI
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.webkit:webkit:1.11.0")

    // Retrofit y Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    //icons
    implementation("androidx.compose.material:material-icons-extended:1.7.1")

    //localizacion
    implementation("androidx.compose.material:material-icons-extended:1.7.1")

    // Mapbox
    implementation("com.mapbox.maps:android:11.6.0")
    implementation("com.mapbox.extension:maps-compose:11.6.0")
}