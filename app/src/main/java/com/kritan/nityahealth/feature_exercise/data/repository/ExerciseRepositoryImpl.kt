package com.kritan.nityahealth.feature_exercise.data.repository

import com.kritan.nityahealth.base.api.MyApi
import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.base.utils.emitDataOrNull
import com.kritan.nityahealth.feature_exercise.data.api.ExerciseApi
import com.kritan.nityahealth.feature_exercise.data.models.ExerciseBridge
import com.kritan.nityahealth.feature_exercise.data.models.ExercisePackage
import com.kritan.nityahealth.feature_exercise.data.models.ExerciseTraining
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExerciseRepositoryImpl(val api: ExerciseApi) : ExerciseRepository {
    override suspend fun getAllFitness(): Flow<Resource<List<ExercisePackage>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteFitness = MyApi.fetchFromRemote {
                api.getAllExercises()
            }
            emitDataOrNull(remoteFitness?.posts)

            emit(Resource.Loading(false))
        }

    }

    override suspend fun getExerciseBridge(id: Int): Flow<Resource<List<ExerciseBridge>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteExerciseBridge = MyApi.fetchFromRemote {
                api.getExerciseBridge(id)
            }
            emitDataOrNull(remoteExerciseBridge?.mPackage)

            emit(Resource.Loading(false))
        }
    }

    override suspend fun getTraining(id: Int): Flow<Resource<List<ExerciseTraining>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteTraining = MyApi.fetchFromRemote {
                api.getTraining(id)
            }
            emitDataOrNull(remoteTraining?.training)

            emit(Resource.Loading(false))
        }
    }
}