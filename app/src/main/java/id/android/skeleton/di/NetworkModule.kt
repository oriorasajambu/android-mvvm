package id.android.skeleton.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.android.skeleton.common.Constants
import id.android.skeleton.services.HeaderInterceptor
import id.android.skeleton.services.MockInterceptor
import id.android.skeleton.services.TokenInterceptor
import id.android.skeleton.services.session.SessionUseCase
import id.android.skeleton.common.BuildConfigUtils
import io.reactivex.rxjava3.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfigUtils.getBaseUrl())
            addConverterFactory(gsonConverterFactory)
            addCallAdapterFactory(rxJava3CallAdapterFactory)
            client(okHttpClient)
        }.build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        mockInterceptor: MockInterceptor,
        headerInterceptor: HeaderInterceptor,
        tokenInterceptor: TokenInterceptor,
        logInterceptor: HttpLoggingInterceptor,
        chucker: ChuckerInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            connectTimeout(Constants.Network.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(Constants.Network.READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(Constants.Network.WRITE_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(logInterceptor)
            addInterceptor(chucker)
            addInterceptor(headerInterceptor)
            addInterceptor(tokenInterceptor)
            addInterceptor(mockInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    @Provides
    @Singleton
    fun providesChucker(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).apply {
            collector(ChuckerCollector(context))
            maxContentLength(250000L)
            alwaysReadResponseBody(false)
        }.build()
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor.Builder().build()
    }

    @Provides
    @Singleton
    fun provideTokenInterceptor(session: SessionUseCase): TokenInterceptor {
        return TokenInterceptor(session)
    }

    @Provides
    @Singleton
    fun provideMockInterceptor(@ApplicationContext context: Context): MockInterceptor {
        return MockInterceptor(context)
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}