import configs.Configs
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.symbolic.processing)
    alias(libs.plugins.firebase.services)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = Configs.NAMESPACE
    compileSdk = Configs.COMPILE_SDK

    signingConfigs {
        create(Configs.Signing.CONTAINER_NAME) {
            // Load keystore
            val keyStoreFile = rootProject.file(Configs.Signing.KEY_STORE_FILE_NAME)
            val keyStoreProperties = Properties()
            keyStoreProperties.load(FileInputStream(keyStoreFile))
            keyAlias = keyStoreProperties[Configs.Signing.KEY_ALIAS].toString()
            keyPassword = keyStoreProperties[Configs.Signing.KEY_PASSWORD].toString()
            storeFile = File(keyStoreProperties[Configs.Signing.STORE_FILE].toString())
            storePassword = keyStoreProperties[Configs.Signing.STORE_PASSWORD].toString()
        }
    }

    defaultConfig {
        applicationId = Configs.APPLICATION_ID
        minSdk = Configs.MIN_SDK
        targetSdk = Configs.TARGET_SDK
        versionCode = Configs.VERSION_CODE
        versionName = Configs.VERSION_NAME
        multiDexEnabled = Configs.MULTI_DEX
        testInstrumentationRunner = Configs.TEST_RUNNER
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(Configs.ProGuard.DEFAULT_PRO_GUARD),
                Configs.ProGuard.PRO_GUARD
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(Configs.ProGuard.DEFAULT_PRO_GUARD),
                Configs.ProGuard.PRO_GUARD
            )
        }
    }

    flavorDimensions.add(Configs.Variant.VERSION)
    productFlavors {
        enumValues<Configs.Variant.Flavours>().forEach { flavor ->
            val localProperties = getLocalProperties(flavor.propertiesFileName)
            create(flavor.variant) {
                applicationId = flavor.appId
                dimension = Configs.Variant.VERSION
                with(Configs.PlaceHolder) {
                    manifestPlaceholders[APP_NAME] = flavor.appName
                    localProperties.forEach { (key, value) ->
                        manifestPlaceholders[key] = value
                        buildConfigField("String", key, "\"$value\"")
                    }
                }
                tasks.register<Copy>(flavor.taskName) {
                    description = flavor.taskDescription
                    group = JavaBasePlugin.DOCUMENTATION_GROUP
                    from(flavor.sourcePath)
                    include(flavor.includes)
                    into(flavor.targetPath)
                }
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = Configs.JVM_TARGET
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    packaging {
        resources.merges.addAll(Configs.Packaging.Type.MERGE.elements)
        resources.excludes.addAll(Configs.Packaging.Type.EXCLUDE.elements)
    }
}

dependencies {
    //Core
    implementation(platform(libs.kotlin.bom))
    implementation(libs.bundles.core)
    coreLibraryDesugaring(libs.bundles.core.jdk)

    //UI
    implementation(libs.bundles.ui)

    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)

    //Dagger
    implementation(libs.bundles.dagger)
    ksp(libs.bundles.ksp)

    //Reactive
    implementation(libs.bundles.reactive)

    //Network
    implementation(libs.bundles.network)
    debugImplementation(libs.bundles.network.debug)
    releaseImplementation(libs.bundles.network.release)

    //Security
    implementation(libs.bundles.security)

    //Testing
    testImplementation(libs.bundles.testing)
    androidTestImplementation(libs.bundles.testing.android)
}

fun getLocalProperties(
    fileName: String,
): Map<String, String> {
    val variantFile = File(fileName)
    if (variantFile.exists()) {
        val properties = Properties()
        val props = mutableMapOf<String, String>()
        properties.load(rootProject.file(variantFile).reader())
        properties.forEach { key, value ->
            props[key.toString()] = value.toString()
        }
        return props
    } else {
        throw IllegalArgumentException("$fileName not found, have you create $fileName yet?")
    }
}