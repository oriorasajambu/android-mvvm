package id.android.skeleton.di

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.android.skeleton.common.logger.LogUseCase
import id.android.skeleton.common.logger.Logger
import id.android.skeleton.common.reactive.AppSchedulerProvider
import id.android.skeleton.common.reactive.SchedulerProvider
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
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}