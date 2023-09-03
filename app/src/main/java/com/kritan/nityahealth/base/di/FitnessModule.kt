package com.kritan.nityahealth.base.di

import android.content.Context
import androidx.room.Room
import com.kritan.nityahealth.feature_exercise.data.api.ExerciseApi
import com.kritan.nityahealth.feature_exercise.data.local.TrainingDatabase
import com.kritan.nityahealth.feature_exercise.data.local.TrainingRepository
import com.kritan.nityahealth.feature_exercise.data.repository.ExerciseRepository
import com.kritan.nityahealth.feature_exercise.data.repository.ExerciseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun providesTrainingDatabase(@ApplicationContext appContext: Context): TrainingDatabase {
        return Room.databaseBuilder(appContext, TrainingDatabase::class.java, "training_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesTrainingRepository(db: TrainingDatabase): TrainingRepository {
        return TrainingRepository(db)
    }


}