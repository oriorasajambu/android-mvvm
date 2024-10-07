package id.android.skeleton.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.android.skeleton.common.reactive.AppSchedulerProvider
import id.android.skeleton.common.reactive.SchedulerProvider
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CommonModule {
    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}