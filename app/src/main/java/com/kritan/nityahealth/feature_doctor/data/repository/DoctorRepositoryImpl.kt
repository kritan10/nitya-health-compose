package com.kritan.nityahealth.feature_doctor.data.repository

import com.google.gson.Gson
import com.kritan.nityahealth.base.api.MyApi
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.base.utils.emitDataOrNull
import com.kritan.nityahealth.feature_doctor.data.api.DoctorsApi
import com.kritan.nityahealth.feature_doctor.data.models.Doctor
import com.kritan.nityahealth.feature_doctor.data.models.DoctorDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DoctorRepositoryImpl(private val api: DoctorsApi) : DoctorRepository {

    override suspend fun getAllDoctors(): Flow<Resource<List<Doctor>>> {
        return flow {
            emit(Resource.Loading(true))

            val doctorsApiResponse = MyApi.fetchFromRemote {
                api.getAllDoctors()
            }

            println("Doctor respose = ${Gson().toJson(doctorsApiResponse)}")

            emitDataOrNull(doctorsApiResponse?.data?.doctors, doctorsApiResponse?.message)

            emit(Resource.Loading(false))
        }
    }

    override suspend fun getDoctorDetail(id: Int): Flow<Resource<DoctorDetail>> {
        return flow {
            emit(Resource.Loading(true))

            val doctorApiResponse = MyApi.fetchFromRemote {
                api.getDoctorDetails(id)
            }
            emitDataOrNull(doctorApiResponse?.data?.doctor, doctorApiResponse?.message)

            emit(Resource.Loading(false))
        }
    }
}