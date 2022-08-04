import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.extra

class GradleConfigPropertyNotFound(prop: String) : Throwable(
    message = "The property `$prop` is required but not found. Did you initialized on the gradle.properties file."
)

inline fun <reified T : ExtensionAware> T.propertyOrNull(name: String): String? =
    extra[name]?.toString()

inline fun <reified T : ExtensionAware> T.propertyOrThrow(name: String): String =
    propertyOrNull(name) ?: throw GradleConfigPropertyNotFound(name)

inline fun <reified T : ExtensionAware> T.propertyIntOrDefault(name: String, default: Int = 0): Int =
    propertyOrNull(name)?.toIntOrNull() ?: default

inline fun <reified T : ExtensionAware> T.propertyIntOrThrow(name: String): Int =
    propertyOrThrow(name).toInt()

inline fun <reified T : ExtensionAware> T.propertyListOrEmpty(name: String, delimiter: String = ", "): List<String> =
    propertyOrNull(name)?.split(delimiter).orEmpty()

inline val <T : ExtensionAware> T.PROP_APP_NAME get() = "project.config.app.name"
val Project.APP_QUALIFIED_NAME_PROP get() = "project.config.app.qualifiedName"

val Project.COMPILE_SDK_PROP get() = "project.config.sdk.compileVersion"
val Project.TARGET_SDK_PROP get() = "project.config.sdk.targetVersion"
val Project.MIN_SDK_PROP get() = "project.config.sdk.minVersion"

val <T : ExtensionAware> T.PROP_PREVIEW_FEATURES get() = "project.config.repoSettings.previewFeatures"
val <T : ExtensionAware> T.PROP_ANDROID_VERSION get() = "project.config.plugins.androidVersion"
val <T : ExtensionAware> T.PROP_KOTLIN_VERSION get() = "project.config.plugins.kotlinVersion"
