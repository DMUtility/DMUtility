rootProject.name = "DMUtility"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://storage.googleapis.com/download.flutter.io")
        maven(url = "https://jitpack.io")
    }
}

//apply(
//    from = rootProject.projectDir.resolve(
//        relative = ".android/include_flutter.groovy"
//    )
//)

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0" apply true
}

include(":app")