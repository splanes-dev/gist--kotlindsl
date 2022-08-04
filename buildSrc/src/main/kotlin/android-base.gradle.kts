plugins {
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {

    "kapt"("com.google.dagger:hilt-android-compiler:2.40.5")
    "api"("com.google.dagger:hilt-android:2.40.5")
    "api"("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    "api"("androidx.hilt:hilt-navigation-compose:1.0.0")
    "api"("com.jakewharton.timber:timber:5.0.1")
    "api"("androidx.core:core-ktx:1.8.0")
    "api"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
    "api"("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.2")

    "testApi"("junit:junit:4.13.2")
    "testApi"("io.mockk:mockk:1.12.5")
    "testApi"("io.mockk:mockk-agent-jvm:1.12.5")

    "androidTestApi"("io.mockk:mockk-android:1.12.5")
    "androidTestApi"("androidx.test.ext:junit:1.1.3")
    "androidTestApi"("androidx.test.espresso:espresso-core:3.4.0")
}
