package id.android.skeleton.di

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.android.skeleton.services.firebase.FirebaseInteractor
import id.android.skeleton.services.firebase.FirebaseUseCase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FirebaseModule {
    @Provides
    @Singleton
    fun provideFirebaseMessaging(): FirebaseMessaging {
        return FirebaseMessaging.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseCrashlytic(): FirebaseCrashlytics {
        return FirebaseCrashlytics.getInstance().apply {
            isCrashlyticsCollectionEnabled = true
        }
    }

    @Provides
    @Singleton
    fun provideFirebaseMessagingInteractor(
        messaging: FirebaseMessaging,
        crashlytics: FirebaseCrashlytics,
    ): FirebaseUseCase {
        return FirebaseInteractor(messaging, crashlytics)
    }
}