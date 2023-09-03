package com.kritan.nityahealth.feature_exercise.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TrainingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(training: Training)

    @Query("SELECT * FROM training")
    suspend fun getAll(): List<Training>
}