package configs

object Configs {
    const val NAMESPACE = "id.android.skeleton"
    const val APPLICATION_ID = "id.android.skeleton"
    const val MIN_SDK = 29
    const val COMPILE_SDK = 35
    const val TARGET_SDK = 35
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"
    const val TEST_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    const val JVM_TARGET = "17"
    const val MULTI_DEX = true


    object Packaging {
        enum class Type(val elements: List<String>) {
            EXCLUDE(elements = listOf("/META-INF/{AL2.0,LGPL2.1}")),
            MERGE(elements = listOf("META-INF/LICENSE.md", "META-INF/LICENSE-notice.md")),
        }
    }

    object ProGuard {
        const val DEFAULT_PRO_GUARD = "proguard-android-optimize.txt"
        const val PRO_GUARD = "proguard-rules.pro"
    }

    object PlaceHolder {
        const val APP_NAME = "APP_NAME"
    }

    object Variant {
        const val VERSION = "version"

        enum class Flavours(
            val variant: String,
            val propertiesFileName: String,
            val appId: String,
            val appName: String,
            val taskName: String,
            val taskDescription: String,
            val sourcePath: String,
            val includes: List<String>,
            val targetPath: String,
        ) {
            DEV(
                variant = "dev",
                propertiesFileName = "dev.properties",
                appId = "id.android.skeleton.dev",
                appName = "Android Skeleton DEV",
                taskName = "devCopy",
                taskDescription = "Copy All Source Code to DEV Path",
                sourcePath = "src/dev/",
                includes = listOf("*.json"),
                targetPath = ".",
            ),
        }
    }

    object Signing {
        const val CONTAINER_NAME = "release"
        const val KEY_STORE_FILE_NAME = "keystore.properties"
        const val KEY_ALIAS = "KEY_ALIAS"
        const val KEY_PASSWORD = "KEY_PASSWORD"
        const val STORE_FILE = "STORE_FILE"
        const val STORE_PASSWORD = "STORE_PASSWORD"
    }
}