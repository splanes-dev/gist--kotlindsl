@file:Suppress("UnstableApiUsage")

object Constants {
    const val PROP_PREVIEW_FEATURES = "project.config.repoSettings.previewFeatures"
    const val PROP_ANDROID_VERSION = "project.config.plugins.androidVersion"
    const val PROP_KOTLIN_VERSION = "project.config.plugins.kotlinVersion"
    const val PROP_APP_NAME = "project.config.app.name"
}

fun Settings.propertyListOrEmpty(name: String): List<String> =
    extra[name]?.toString()?.split(", ").orEmpty()

fun Settings.propertyOrEmpty(name: String): String =
    extra[name]?.toString().orEmpty()

val previewFeatureList: List<String> =
    settings.extra["project.config.repoSettings.previewFeatures"].toString().split(", ")

previewFeatureList.forEach(::enableFeaturePreview)

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        val androidVersion: String =
            settings.extra["project.config.plugins.androidVersion"].toString()
        val projectConfigPluginsKotlinVersion: String =
            settings.extra["project.config.plugins.kotlinVersion"].toString()
        id("com.android.application") version androidVersion
        id("com.android.library") version androidVersion
        id("org.jetbrains.kotlin.android") version projectConfigPluginsKotlinVersion
        id("org.jetbrains.kotlin.kapt") version projectConfigPluginsKotlinVersion
        // id("com.google.gms.google-services")
        id("dagger.hilt.android.plugin")
        id("kotlin-parcelize")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

val projectConfigAppName: String =
    settings.extra["project.config.app.name"]?.toString().orEmpty()
rootProject.name = projectConfigAppName

include(":app")
