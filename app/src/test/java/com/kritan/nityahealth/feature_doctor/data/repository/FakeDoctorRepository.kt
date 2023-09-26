package com.kritan.nityahealth.feature_doctor.data.repository

import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_doctor.data.models.Doctor
import com.kritan.nityahealth.feature_doctor.data.models.DoctorDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDoctorRepository(val shouldReturnError: Boolean) : DoctorRepository {
    override suspend fun getAllDoctors(): Flow<Resource<List<Doctor>>> {
        return flow {
            emit(Resource.Loading())
            delay(2000)
            emit(
                Resource.Success(
                    listOf(
                        Doctor(
                            id = 1,
                            name = null,
                            email = null,
                            position = null,
                            phone = null,
                            experience = null,
                            qualification = null,
                            speciality = null,
                            nmc = null,
                            website = null,
                            image = null
                        )
                    ), message = "Success"
                )
            )
            emit(Resource.Loading())
        }
    }

    override suspend fun getDoctorDetail(id: Int): Flow<Resource<DoctorDetail>> {
        return flow {
            emit(Resource.Loading())
            delay(2000)
            emit(
                Resource.Success(
                    DoctorDetail(
                        id = id,
                        name = "Cole Baldwin",
                        email = "don.lang@example.com",
                        position = "vocent",
                        phone = "(830) 234-8525",
                        nmc = "sale",
                        website = "ius",
                        image = "appareat",
                        location = emptyList(),
                        experiences = emptyList(),
                        specialities = emptyList(),
                        qualifications = emptyList()
                    ), message = "Success"
                )
            )
            emit(Resource.Loading())
        }
    }
}