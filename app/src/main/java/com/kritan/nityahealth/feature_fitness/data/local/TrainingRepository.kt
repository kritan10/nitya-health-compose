package com.kritan.nityahealth.feature_fitness.data.local

import javax.inject.Inject

class TrainingRepository @Inject constructor(
    private val db: TrainingDatabase
) {
    suspend fun getAllTrainings(): List<Training> {
        return db.trainingDao().getAll()
    }

    suspend fun insertTraining(training: Training) {
        db.trainingDao().insert(training)
    }
}