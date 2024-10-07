package id.android.skeleton.features.startup.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.android.skeleton.features.startup.data.StartupApiServices
import id.android.skeleton.features.startup.data.StartupDataRepository
import id.android.skeleton.features.startup.data.StartupRepository
import id.android.skeleton.features.startup.domain.StartupInteractor
import id.android.skeleton.features.startup.domain.StartupUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class StartupModule {
    companion object {
        @Provides
        @Singleton
        fun providesApiEndPoint(retrofit: Retrofit): StartupApiServices {
            return retrofit.create(StartupApiServices::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun provideRepository(repository: StartupDataRepository): StartupRepository

    @Binds
    @Singleton
    abstract fun provideInteractor(interactor: StartupInteractor): StartupUseCase
}