package com.kritan.nityahealth.feature_doctor.domain

import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_doctor.data.models.Doctor
import com.kritan.nityahealth.feature_doctor.data.models.DoctorDetail
import kotlinx.coroutines.flow.Flow

interface DoctorRepository {
    suspend fun getAllDoctors(): Flow<Resource<List<Doctor>>>

    suspend fun getDoctorDetail(id: Int): Flow<Resource<DoctorDetail>>
}