// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
}
buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.47")
    }
}