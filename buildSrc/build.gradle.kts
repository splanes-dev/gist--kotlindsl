plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    maven(url = "https://plugins.gradle.org/m2/")
}

dependencies {

    implementation("com.android.tools.build:gradle:7.4.0-alpha08")
    implementation(kotlin("gradle-plugin", "1.6.10"))
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.40.5")
    // implementation("com.google.gms:google-services:4.3.13")
}

configurations {
    implementation {
        resolutionStrategy.failOnVersionConflict()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks {
    assemble {

    }
    compileKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
        kotlinOptions.freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    }
    test {
        testLogging.showCauses = true
        testLogging.showExceptions = true
        testLogging.showStackTraces = true
    }
}

abstract class ChangeLoggerTask : DefaultTask() {

    @Incremental
    @InputDirectory
    abstract fun inputDir(): DirectoryProperty

    @Inject
    abstract fun factory(): ObjectFactory

    @Inject
    fun worker(): WorkerExecutor {
        throw NotImplementedError()
    }

    @TaskAction
    fun execute(inputChanges: InputChanges) {

        wi

        var output: String = ""
        val changeMap = inputChanges.getFileChanges(inputDir()).groupBy { f -> f.changeType }

        output += "Change log" + "\n"
        output += "----------" + "\n" + "\n" + "\n"

        output += "|> Removed files" + "\n"
        output += "|" + "\n"
        changeMap[ChangeType.REMOVED]?.forEach { file ->
            output += "|--> " + file.file.name + "\n"
        } ?: run { output += "| No files removed." + "\n" }

        output += "\n"
        output += "|> Added files" + "\n"
        output += "|" + "\n"
        changeMap[ChangeType.ADDED]?.forEach { file ->
            output += "|--> " + file.file.name + "\n"
        } ?: run { output += "| No files added." + "\n" }

        output += "\n"
        output += "|> Modified files" + "\n"
        output += "|" + "\n"
        changeMap[ChangeType.MODIFIED]?.forEach { file ->
            output += "|--> " + file.file.name + "\n"
        } ?: run { output += "| No files modified." + "\n" }

        println(output)
        layout.projectDirectory
            .dir("file-change-logs")
            .file("log-${System.currentTimeMillis()}.txt").apply {
                asFile.appendText(output)
            }
    }
}
