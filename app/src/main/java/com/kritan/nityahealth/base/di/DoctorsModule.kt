package com.kritan.nityahealth.base.di

import com.kritan.nityahealth.feature_doctor.data.api.DoctorsApi
import com.kritan.nityahealth.feature_doctor.data.repository.DoctorRepositoryImpl
import com.kritan.nityahealth.feature_doctor.domain.DoctorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DoctorsModule {

    @Singleton
    @Provides
    fun provideDoctorsApi(retrofit: Retrofit): DoctorsApi {
        return retrofit.create(DoctorsApi::class.java)
    }


    @Singleton
    @Provides
    fun providesDoctorRepository(api: DoctorsApi): DoctorRepository {
        return DoctorRepositoryImpl(api)
    }
}