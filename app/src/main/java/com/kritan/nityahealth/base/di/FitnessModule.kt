package com.kritan.nityahealth.base.di

import com.kritan.nityahealth.feature_fitness.data.api.ExerciseApi
import com.kritan.nityahealth.feature_fitness.data.repository.ExerciseRepository
import com.kritan.nityahealth.feature_fitness.data.repository.ExerciseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FitnessModule {

    @Singleton
    @Provides
    fun providesFitnessApi(retrofit: Retrofit): ExerciseApi {
        return retrofit.create(ExerciseApi::class.java)
    }

    @Singleton
    @Provides
    fun providesFitnessRepository(api: ExerciseApi): ExerciseRepository {
        return ExerciseRepositoryImpl(api)
    }
}