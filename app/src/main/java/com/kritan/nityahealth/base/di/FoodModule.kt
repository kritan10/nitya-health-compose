package com.kritan.nityahealth.base.di

import com.kritan.nityahealth.feature_food.data.repository.FoodRepository
import com.kritan.nityahealth.feature_food.data.repository.FoodRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FoodModule {

    @Singleton
    @Provides
    fun providesFoodRepository(httpClient: HttpClient): FoodRepository {
        return FoodRepositoryImpl(httpClient)
    }

}