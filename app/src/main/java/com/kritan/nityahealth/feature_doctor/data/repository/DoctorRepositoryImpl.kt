package com.kritan.nityahealth.feature_doctor.data.repository

import android.util.Log
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_doctor.data.api.DoctorsApi
import com.kritan.nityahealth.feature_doctor.data.models.Doctor
import com.kritan.nityahealth.feature_doctor.data.models.DoctorDetail
import com.kritan.nityahealth.feature_doctor.domain.DoctorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class DoctorRepositoryImpl(private val api: DoctorsApi) : DoctorRepository {

    override suspend fun getAllDoctors(): Flow<Resource<List<Doctor>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteDoctors: List<Doctor>? = try {
                val response = api.getAllDoctors()
                Log.d("response", response.body()?.data?.doctors.toString())
                response.body()?.data?.doctors
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteDoctors?.let {
                emit(Resource.Success(it))
            }

            emit(Resource.Loading(false))
        }
    }

    override suspend fun getDoctorDetail(id: Int): Flow<Resource<DoctorDetail>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteDoctorDetails: DoctorDetail? = try {
                val response = api.getDoctorDetails(id)
                Log.d("response", response.body()?.data?.doctor.toString())
                response.body()?.data?.doctor
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteDoctorDetails?.let {
                emit(Resource.Success(it))
            }

            emit(Resource.Loading(false))
        }
    }
}