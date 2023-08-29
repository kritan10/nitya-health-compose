package com.kritan.nityahealth.feature_fitness.data.repository

import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_fitness.data.models.ExerciseBridge
import com.kritan.nityahealth.feature_fitness.data.models.ExercisePackage
import com.kritan.nityahealth.feature_fitness.data.models.ExerciseTraining
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    suspend fun getAllFitness(): Flow<Resource<List<ExercisePackage>>>

    suspend fun getExerciseBridge(id: Int): Flow<Resource<List<ExerciseBridge>>>

    suspend fun getTraining(id: Int): Flow<Resource<List<ExerciseTraining>>>

}