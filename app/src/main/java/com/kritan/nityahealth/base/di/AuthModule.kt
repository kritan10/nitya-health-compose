package com.kritan.nityahealth.base.di

import com.kritan.nityahealth.feature_auth.data.api.AuthApi
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

object AuthModule {

    @Singleton
    @Provides
    fun providesAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)

    }
}