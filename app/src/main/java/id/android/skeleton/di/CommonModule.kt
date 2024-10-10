package id.android.skeleton.di

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.android.skeleton.common.logger.LogUseCase
import id.android.skeleton.common.logger.Logger
import id.android.skeleton.common.reactive.AppSchedulerProvider
import id.android.skeleton.common.reactive.SchedulerProvider
import id.android.skeleton.services.lifecycle.AppLifeCycleInteractor
import id.android.skeleton.services.lifecycle.AppLifeCycleUseCase
import id.android.skeleton.services.notification.PushNotificationManager
import id.android.skeleton.services.notification.PushNotificationUseCase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CommonModule {
    @Provides
    @Singleton
    fun provideLogger(): LogUseCase {
        return Logger()
    }

    @Provides
    @Singleton
    fun provideLifeCycleOwner(): Lifecycle {
        return ProcessLifecycleOwner.get().lifecycle
    }

    @Provides
    @Singleton
    fun provideLifeCycleUseCase(
        lifecycle: Lifecycle,
        logger: LogUseCase,
    ): AppLifeCycleUseCase {
        return AppLifeCycleInteractor(lifecycle, logger)
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    fun providePushNotificationInteractor(
        @ApplicationContext context: Context,
        appLifeCycleUseCase: AppLifeCycleUseCase,
    ): PushNotificationUseCase {
        return PushNotificationManager(context, appLifeCycleUseCase)
    }
}