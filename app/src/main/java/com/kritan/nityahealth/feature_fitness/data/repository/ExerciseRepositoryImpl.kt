package com.kritan.nityahealth.feature_fitness.data.repository

import android.util.Log
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_fitness.data.api.ExerciseApi
import com.kritan.nityahealth.feature_fitness.data.models.ExerciseBridge
import com.kritan.nityahealth.feature_fitness.data.models.ExercisePackage
import com.kritan.nityahealth.feature_fitness.data.models.ExerciseTraining
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ExerciseRepositoryImpl(val api: ExerciseApi) : ExerciseRepository {
    override suspend fun getAllFitness(): Flow<Resource<List<ExercisePackage>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteFitness: List<ExercisePackage>? = try {
                val response = api.getAllExercises()
                Log.d("response", response.body()?.data?.posts.toString())
                response.body()?.data?.posts
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteFitness?.let {
                emit(Resource.Success(it))
            }

            emit(Resource.Loading(false))
        }

    }

    override suspend fun getExerciseBridge(id: Int): Flow<Resource<List<ExerciseBridge>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteExerciseBridge: List<ExerciseBridge>? = try {
                val response = api.getExerciseBridge(id)
                Log.d("response", response.body()?.data?.mPackage.toString())
                response.body()?.data?.mPackage
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteExerciseBridge?.let {
                emit(Resource.Success(it))
            }

            emit(Resource.Loading(false))
        }
    }

    override suspend fun getTraining(id: Int): Flow<Resource<List<ExerciseTraining>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteTraining: List<ExerciseTraining>? = try {
                val response = api.getTraining(id)
                Log.d("response", response.body()?.data?.training.toString())
                response.body()?.data?.training
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteTraining?.let {
                emit(Resource.Success(it))
            }

            emit(Resource.Loading(false))
        }
    }
}