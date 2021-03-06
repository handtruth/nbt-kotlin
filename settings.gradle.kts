pluginManagement {
    repositories {
        gradlePluginPortal()
        jcenter()
    }
    val kotlinVersion: String by settings
    val gitAndroidVersion: String by settings
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("org.jetbrains.kotlin"))
                useVersion(kotlinVersion)
        }
    }
    plugins {
        id("com.gladed.androidgitversion") version gitAndroidVersion
    }
}

rootProject.name = "nbt-kotlin"
