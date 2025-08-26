import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(notation = libs.plugins.kotlinMultiplatform)
    alias(notation = libs.plugins.androidApplication)
    alias(notation = libs.plugins.composeMultiplatform)
    alias(notation = libs.plugins.composeCompiler)
    alias(notation = libs.plugins.composeHotReload)
}

object AppConfig {
    /** 包名 */
    const val PACKAGE_NAME: String = "com.wyq0918dev.dmutillity"

    /** 版本名 */
    const val VERSION_NAME: String = "1.0.0"

    /** 版本号 */
    const val VERSION_CODE: Int = 1
}

object AndroidConfig {
    const val TARGET_SDK: Int = 36
    const val MIN_SDK: Int = 24
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class) compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    sourceSets {
        androidMain.dependencies {
//            implementation(dependencyNotation = project(path = ":flutter"))
//            implementation(dependencyNotation = project(path = ":flutter_mixed"))
            implementation(dependencyNotation = compose.preview)
            implementation(dependencyNotation = libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(dependencyNotation = compose.runtime)
            implementation(dependencyNotation = compose.foundation)
            implementation(dependencyNotation = compose.material3)
            implementation(dependencyNotation = compose.ui)
            implementation(dependencyNotation = compose.components.resources)
            implementation(dependencyNotation = compose.components.uiToolingPreview)
            implementation(dependencyNotation = libs.androidx.lifecycle.viewmodelCompose)
            implementation(dependencyNotation = libs.androidx.lifecycle.runtimeCompose)
        }
        jvmMain.dependencies {
            implementation(dependencyNotation = compose.desktop.currentOs)
            implementation(dependencyNotation = libs.kotlinx.coroutinesSwing)
        }
    }
}

android {
    namespace = AppConfig.PACKAGE_NAME
    compileSdk = AndroidConfig.TARGET_SDK

    defaultConfig {
        applicationId = AppConfig.PACKAGE_NAME
        minSdk = AndroidConfig.MIN_SDK
        targetSdk = AndroidConfig.TARGET_SDK
        versionCode = AppConfig.VERSION_CODE
        versionName = AppConfig.VERSION_NAME
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = AppConfig.PACKAGE_NAME
            packageVersion = AppConfig.VERSION_NAME

            windows {
                menu = true
                shortcut = true
                dirChooser = true // 可自行选择安装目录
//// 设置图标
//                iconFile.set(project.file("launcher/icon.ico"))
//                upgradeUuid = "xxxx-xxxxxxx-xxxxx"
            }
        }
    }
}

dependencies {
    debugImplementation(dependencyNotation = compose.uiTooling)
}