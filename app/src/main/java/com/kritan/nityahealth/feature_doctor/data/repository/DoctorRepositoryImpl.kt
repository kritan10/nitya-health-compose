package com.kritan.nityahealth.feature_doctor.data.repository

import com.kritan.nityahealth.base.api.MyApi
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.base.utils.emitDataOrNull
import com.kritan.nityahealth.feature_doctor.data.api.DoctorDTO
import com.kritan.nityahealth.feature_doctor.data.api.DoctorDetailsDTO
import com.kritan.nityahealth.feature_doctor.data.api.DoctorsApi
import com.kritan.nityahealth.feature_doctor.data.models.Doctor
import com.kritan.nityahealth.feature_doctor.data.models.DoctorDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DoctorRepositoryImpl(private val api: DoctorsApi) : DoctorRepository {

    override suspend fun getAllDoctors(): Flow<Resource<List<Doctor>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteDoctors: DoctorDTO? = MyApi.fetchFromRemote {
                api.getAllDoctors()
            }
            emitDataOrNull(remoteDoctors?.doctors)

            emit(Resource.Loading(false))
        }
    }

    override suspend fun getDoctorDetail(id: Int): Flow<Resource<DoctorDetail>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteDoctorDetails: DoctorDetailsDTO? = MyApi.fetchFromRemote {
                api.getDoctorDetails(id)
            }
            emitDataOrNull(remoteDoctorDetails?.doctor)

            emit(Resource.Loading(false))
        }
    }
}