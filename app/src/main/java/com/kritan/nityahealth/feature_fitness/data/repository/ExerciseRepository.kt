package com.kritan.nityahealth.feature_fitness.data.repository

import com.kritan.nityahealth.base.utils.Resource
import com.kritan.nityahealth.feature_fitness.data.models.ExercisePackage
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    suspend fun getAllFitness(): Flow<Resource<List<ExercisePackage>>>
}