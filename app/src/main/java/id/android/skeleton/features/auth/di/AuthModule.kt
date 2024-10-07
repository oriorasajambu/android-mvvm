package id.android.skeleton.features.auth.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.android.skeleton.features.auth.data.AuthApiServices
import id.android.skeleton.features.auth.data.AuthDataRepository
import id.android.skeleton.features.auth.data.AuthRepository
import id.android.skeleton.features.auth.domain.AuthInteractor
import id.android.skeleton.features.auth.domain.AuthUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AuthModule {
    companion object {
        @Provides
        @Singleton
        fun providesApiEndPoint(retrofit: Retrofit): AuthApiServices {
            return retrofit.create(AuthApiServices::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun provideRepository(repository: AuthDataRepository): AuthRepository

    @Binds
    @Singleton
    abstract fun provideInteractor(interactor: AuthInteractor): AuthUseCase
}