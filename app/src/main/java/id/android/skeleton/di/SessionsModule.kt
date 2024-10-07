package id.android.skeleton.di

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.android.skeleton.services.session.SessionInteractor
import id.android.skeleton.services.session.SessionUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SessionsModule {
    companion object {
        private const val KEY_ALIAS: String = "_androidx_security_master_key_"
        private const val SECRET_SHARED_PREF: String = "secret_shared_prefs"
    }

    @Provides
    @Singleton
    fun provideKeyGenSpec(): KeyGenParameterSpec {
        return KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).apply {
            setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            setKeySize(256)
        }.build()
    }

    @Provides
    @Singleton
    fun provideMasterKey(
        @ApplicationContext context: Context,
        spec: KeyGenParameterSpec,
    ): MasterKey {
        return MasterKey.Builder(context).apply {
            setKeyGenParameterSpec(spec)
        }.build()
    }

    @Provides
    @Singleton
    fun provideSharedPreference(
        @ApplicationContext context: Context,
        masterKey: MasterKey,
    ): SharedPreferences {
        return EncryptedSharedPreferences(
            context,
            SECRET_SHARED_PREF,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    @Singleton
    fun provideSessionInteractor(
        sharedPreferences: SharedPreferences,
    ): SessionUseCase {
        return SessionInteractor(sharedPreferences)
    }
}