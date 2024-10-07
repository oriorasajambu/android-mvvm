package id.android.skeleton.features.home.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.android.skeleton.features.home.data.HomeApiServices
import id.android.skeleton.features.home.data.HomeDataRepository
import id.android.skeleton.features.home.data.HomeRepository
import id.android.skeleton.features.home.domain.HomeInteractor
import id.android.skeleton.features.home.domain.HomeUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class HomeModule {
    companion object {
        @Provides
        @Singleton
        fun providesApiEndPoint(retrofit: Retrofit): HomeApiServices {
            return retrofit.create(HomeApiServices::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun provideRepository(repository: HomeDataRepository): HomeRepository

    @Binds
    @Singleton
    abstract fun provideInteractor(interactor: HomeInteractor): HomeUseCase
}