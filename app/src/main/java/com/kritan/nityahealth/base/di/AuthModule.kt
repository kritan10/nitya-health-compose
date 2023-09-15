package com.kritan.nityahealth.base.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.facebook.CallbackManager
import com.kritan.nityahealth.auth.AppAuth
import com.kritan.nityahealth.auth.data.api.AuthApi
import com.kritan.nityahealth.auth.data.api.FacebookApi
import com.kritan.nityahealth.auth.data.repository.AuthRepository
import com.kritan.nityahealth.auth.data.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Singleton
    @Provides
    fun providesAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun providesAuthRepository(authApi: AuthApi): AuthRepository {
        return AuthRepositoryImpl(authApi)
    }

    @Singleton
    @Provides
    fun providesAppAuth(dataStore: DataStore<Preferences>): AppAuth {
        return AppAuth(dataStore)
    }

    @Singleton
    @Provides
    fun providesCallbackManager(): CallbackManager {
        return CallbackManager.Factory.create()
    }

    @Singleton
    @Provides
    fun providesFacebookService(): FacebookApi {
        return Retrofit.Builder()
            .baseUrl("https://graph.facebook.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FacebookApi::class.java)
    }
}